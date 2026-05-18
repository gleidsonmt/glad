

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.Root;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  26/01/2025
 */
public class FlowImpl extends DialogAbstract<Flow> implements Flow {

    private Pos pos = Pos.CENTER;

    public FlowImpl(Root root) {
        super(root);
    }

    /**
     * Every time the properties to avoid getting the same configurations
     * for other callings.
     */
    @Override
    public void reset() {
        anchor = null;
        pos = Pos.CENTER;
        insets = Insets.EMPTY;
        wrapperEffect = null;
        width.unbind();
        height.unbind();
        height.set(-1);
        width.set(-1);
        full = false;
    }

    @Override
    public boolean fits(Region node) {

        if (node.getPrefWidth() >= root.getWidth()) return false;
        if (node.getPrefHeight() >= root.getHeight()) return false;
        if (node.getMinHeight() >= root.getHeight()) return false;
//        if (node.getHeight() >= root.getHeight()) return false;
//        if (node.getWidth() >= root.getWidth()) return false;
        return !(node.getMinWidth() >= root.getWidth());
    }

    /**
     * Clear all modifications doing with flow.
     *
     * @param container The node to change.
     */
    @Override
    public void clearConstraints(Region container) {
        StackPane.clearConstraints(container);
        container.setTranslateY(0);
        container.setTranslateX(0);
        container.setLayoutY(0);
        container.setLayoutX(0);
        container.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        container.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        container.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }

    // Revised
    @Override
    public void remove(Node container) {
        reset();
        root.getChildren().removeAll(container);
    }

    @Override
    public void clear() {
        if (root.getChildren().size() > 1) {
            root.getChildren().remove(1, root.getChildren().size());
        }
    }

    @Override
    public boolean has(Node node) {
        return root.getChildren().contains(node);
    }

    @Override
    public Flow pos(Pos pos) {
        this.pos = pos;
        return this;
    }

    // Revised
    public void show(Region target) {
        StackPane.clearConstraints(content);
        StackPane.setAlignment(content, Pos.TOP_LEFT);

        if (!root.getChildren().contains(content)) {
            root.getChildren().add(content);
        }

        StackPane.setMargin(content, insets);

        this.content.applyCss();
        relocateByNode(target);

    }

    private void relocateByNode(Region target) {
        if (this.height.get() == -1) {
            // if the height of the content has no pref height
            // then the height will be settled by the content
            this.content.maxHeightProperty().bind(this.content.heightProperty());
            this.height.bind(this.content.maxHeightProperty());

        } else { // if the height is settled, the height will be fixed
            this.content.maxHeightProperty().bind(this.height);
            translateBasedOnNodeY(target);
        }

        if (this.width.get() == -1) {
            this.content.maxWidthProperty().bind(this.content.prefWidthProperty());
            this.width.bind(this.content.maxWidthProperty());
        } else {
            this.content.maxWidthProperty().bind(this.width);
            translateBasedOnNodeX(target);
        }

        this.height.addListener((_, _, _) -> translateBasedOnNodeY(target));
        this.width.addListener((_, _, _) -> translateBasedOnNodeX(target));
    }

    // needs to be cut in two other methods to calcalute x and y.
    private void translateBasedOnNodeX(Region target) {
        double x;
        double maxX = getMaxPositionX(pos.getHpos(), target);
        double spaceHorizontal = getSpaceHorizontal(pos.getHpos(), maxX);

        switch (pos.getHpos()) {
            case LEFT -> {
                if (width.get() > spaceHorizontal) {
                    x = maxX - (spaceHorizontal);
                } else x = (maxX - width.get());
            }
            case RIGHT -> {
                if (width.get() > spaceHorizontal) {
                    double cut = width.get() - (spaceHorizontal);
                    x = maxX - cut;
                } else x = maxX;
            }
            case null, default -> {
                if (width.get() / 2 > spaceHorizontal) {
                    double cut = width.get() - spaceHorizontal;
                    x = maxX - cut;
                    // for tests
//                    x = getMaxPositionX(HPos.LEFT,target) - width.get();
                } else {
                    x = (maxX - (width.get() / 2));
                }
            }
        }
        content.setTranslateX(Math.round(x));

    }

    private void translateBasedOnNodeY(Region target) {
        double y;
        double maxY = getMaxPositionY(pos.getVpos(), target);
        double spaceVertical = getSpaceVertical(pos.getVpos(), maxY);

        switch (pos.getVpos()) {
            case BOTTOM -> {
                if (height.get() > spaceVertical) {
                    double cut = height.get() - spaceVertical;
                    y = maxY - cut;
//                    y = getMaxPositionY(VPos.BOTTOM,target) - height.get();
                } else {
                    y = maxY;
                }
            }
            case TOP -> {
                if (height.get() > spaceVertical) {
                    y = maxY - spaceVertical;
                } else {
                    y = maxY - height.get();
                }
            }
            case null, default -> {
                if (height.get() / 2 > spaceVertical) {
                    y = maxY - spaceVertical;
                } else {
                    y = maxY - (height.get() / 2);
                }
            }
        }

        content.setTranslateY(Math.round(y));

    }

    private double getSpaceHorizontal(HPos pos, double maxX) {
        switch (pos) {
            case LEFT -> {
                return maxX; // Tested
            }
            case null, default -> {
                return root.getWidth() - maxX;
            }
        }
    }

    private double getSpaceVertical(VPos pos, double maxY) {
        switch (pos) {
            case TOP -> {
                return maxY; // Tested
            }
            case null, default -> {
                return root.getHeight() - maxY;
            }
        }
    }

    private double getMaxPositionY(VPos pos, Region target) {
        switch (pos) {
            case TOP -> {
                return target.getLocalToSceneTransform().getTy();
            }
            case BOTTOM -> {
                return target.getLocalToSceneTransform().getTy() + target.getHeight();
            }
            case null, default -> {
                return target.getLocalToSceneTransform().getTy() + (target.getHeight() / 2);
            }
        }
    }

    private double getMaxPositionX(HPos pos, Region target) {
        switch (pos) {
            case LEFT -> {
                return target.getLocalToSceneTransform().getTx();
            }
            case RIGHT -> {
                return target.getLocalToSceneTransform().getTx() + target.getWidth();
            }
            case null, default -> {
                return target.getLocalToSceneTransform().getTx() + (target.getWidth() / 2);
            }
        }
    }

    @Override
    public void show(MouseEvent event) {
        StackPane.clearConstraints(content);
        StackPane.setAlignment(content, Pos.TOP_LEFT);

        if (!root.getChildren().contains(content)) {
            root.getChildren().add(content);
        }

        StackPane.setMargin(content, insets);

        this.content.applyCss();

        double height = this.height.get() == -1 ?
                this.content.minHeight(-1) : this.height.get();

        this.content.setMaxHeight(height);
        this.content.setPrefHeight(height);
        this.content.setMinHeight(height);

        double width = this.width.get() == -1 ?
                this.content.minWidth(-1) : this.width.get();

        this.content.setMaxWidth(width);
        this.content.setPrefWidth(width);
        this.content.setMinWidth(width);

        switch (pos.getHpos()) {
            case LEFT -> content.setTranslateX(event.getSceneX() - width);
            case CENTER -> content.setTranslateX(event.getSceneX() - (width / 2));
            case null, default -> content.setTranslateX(event.getSceneX());
        }
        switch (pos.getVpos()) {
            case TOP -> content.setTranslateY(event.getSceneY() - height);
            case CENTER -> content.setTranslateY(event.getSceneY() - (height / 2));
            case null, default -> content.setTranslateX(event.getSceneX());
        }
        reset();
    }

    // needs to be revised
    @Override
    public void show() {

        if (content == null) {
            throw new RuntimeException("Error flow can invoke a null node.");
        }

        if (!root.getChildren().contains(content)) {
            root.getChildren().add(content);
        }

        StackPane.clearConstraints(content);
        StackPane.setAlignment(content, pos);
        StackPane.setMargin(content, insets);

        this.content.applyCss();
        this.content.layout();

        double height = this.height.get() == -1 ?
                this.content.prefHeight(-1) : this.height.get();

        this.content.setPrefHeight(height);
        this.content.setMinHeight(height);

        double width = this.width.get() == -1 ?
                this.content.prefWidth(-1) : this.width.get();

        this.content.setPrefWidth(width);
        this.content.setMinWidth(width);

        switch (anchor) {
            case TOP, BOTTOM -> {
                content.setMaxHeight(height);
                content.setMaxWidth(-1);
            }
            case LEFT, RIGHT -> {
                content.setMaxWidth(width);
                content.setMaxHeight(-1);
            }
            case FULL -> content.setMaxSize(-1, -1);
            case null, default -> {
                content.setMaxWidth(width);
                content.setMaxHeight(height);
            }
        }

        reset();
    }

    @Override
    public void hide() {
        root.getChildren().remove(this.content);
        reset();
    }
}
