package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.FlowItemAbstract;
import io.github.gleidsonmt.glad.base.Root;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class FlowImpl extends FlowItemAbstract<Flow> implements Flow {

    private Pos pos = Pos.CENTER;
    private Region content;
    private final Root root;

    private double width = -1;
    private double height = -1;

    public FlowImpl(Root root) {
        this.root = root;
    }

    /**
     * Every time the properties to avoid getting the same configurations
     * for other callings.
     */
    private void reset() {
        anchor = null;
        pos = Pos.CENTER;
        insets = Insets.EMPTY;
        effect = null;
        width = -1;
        height = -1;
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
    }

    @Override
    public void remove(Node container) {
        root.getChildren().removeAll(container);
    }

    @Override
    public void clear() {
        root.getChildren().remove(1, root.getChildren().size());
    }

    @Override
    public Flow pos(Pos pos) {
        this.pos = pos;
        return this;
    }

    @Override
    public Flow content(Region content) {
        this.content = content;
        return this;
    }

    @Override
    public Flow width(double width) {
        this.width = width;
        return this;
    }

    @Override
    public Flow height(double height) {
        this.height = height;
        return this;
    }

    public void show(Region target) {
        StackPane.clearConstraints(content);
        StackPane.setAlignment(content, Pos.TOP_LEFT);

        if (!root.getChildren().contains(content)) {
            root.getChildren().add(content);
        }

        StackPane.setMargin(content, insets);

        this.content.applyCss();
        relocateByNode(target);
        reset();

    }

    private void relocateByNode(Region target) {
        double x;
        double y;

        double maxX = getMaxPositionX(pos.getHpos(), target);
        double maxY = getMaxPositionY(pos.getVpos(), target);

        double spaceVertical = getSpaceVertical(pos.getVpos(), maxY);
        double spaceHorizontal = getSpaceHorizontal(pos.getHpos(), maxX);

        double height = this.height == -1 ?
                this.content.prefHeight(-1) : this.height;

        this.content.setMaxHeight(height);
        this.content.setPrefHeight(height);
        this.content.setMinHeight(height);

        double width = this.width == -1 ?
                this.content.prefHeight(-1) : this.width;

        this.content.setMaxWidth(width);
        this.content.setPrefWidth(width);
        this.content.setMinWidth(width);

        switch (pos.getHpos()) {
            case LEFT ->  {
                if (width > spaceHorizontal) {
                    x = maxX - (spaceHorizontal);
                } else x = (maxX - width);
            }
            case RIGHT -> {
                if (width > spaceHorizontal) {
                    double cut = width - (spaceHorizontal);
                    x = maxX - cut;
                } else x = maxX;
            }
            case null, default -> {
                if (width / 2 > spaceHorizontal) {
                    double cut = width - spaceHorizontal;
                    x = maxX - cut;
                } else {
                   x = (maxX - (width / 2));
                }
            }
        }

        switch (pos.getVpos()) {
            case BOTTOM -> {
                if (height > spaceVertical) {
                    double cut = height - spaceVertical;
                     y = maxY - cut;
                } else {
                    y = maxY;
                }
            }
            case TOP -> {
                if (height > spaceVertical) {
                    y = maxY - spaceVertical;
                } else {
                    y = maxY - height;
                }
            }
            case null, default -> {
                if (height / 2 > spaceVertical) {
                    y = maxY - spaceVertical;
                } else {
                    y = maxY - (height / 2);
                }
            }
        }
        content.setTranslateX(Math.round(x));
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

        double height = this.height == -1 ?
                this.content.minHeight(-1) : this.height;

        this.content.setMaxHeight(height);
        this.content.setPrefHeight(height);
        this.content.setMinHeight(height);

        double width = this.width == -1 ?
                this.content.minWidth(-1) : this.width;

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

    @Override
    public void show() {
        if (content == null) {
            throw new RuntimeException("Error flow can invoke a null node.");
        }
        StackPane.clearConstraints(content);

        if (content instanceof Region region) {
            switch (anchor) {
                case TOP, BOTTOM -> region.setMaxHeight(Region.USE_PREF_SIZE);
                case LEFT, RIGHT -> region.setMaxWidth(Region.USE_PREF_SIZE);
                case NONE -> region.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
                case null, default -> region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            }
        }

        StackPane.setAlignment(content, pos);
        StackPane.setMargin(content, insets);

        if (!root.getChildren().contains(content)) {
            root.getChildren().add(content);
        }
        reset();
    }

    @Override
    public void hide() {
        root.getChildren().removeLast();
    }
}
