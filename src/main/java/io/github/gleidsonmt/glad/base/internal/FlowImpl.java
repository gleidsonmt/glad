package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.FlowItemAbstract;
import io.github.gleidsonmt.glad.base.Root;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Transform;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static javafx.scene.Node.BASELINE_OFFSET_SAME_AS_HEIGHT;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class FlowImpl extends FlowItemAbstract<Flow> implements Flow {

    private Pos pos = Pos.CENTER;
    private Region content;
    private final Root root;

    public FlowImpl(Root root) {
        this.root = root;
    }


    @Override
    public void openAbsolute(Node container, Pos pos) {
        openAbsolute(container, pos, Insets.EMPTY);
    }

    @Override
    public void openAbsolute(Node container, Pos position, Insets insets) {
        StackPane.clearConstraints(container);

        if (container instanceof Region region) {
            switch (anchor) {
                case TOP, BOTTOM -> region.setMaxHeight(Region.USE_PREF_SIZE);
                case LEFT, RIGHT -> region.setMaxWidth(Region.USE_PREF_SIZE);
                case NONE -> region.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
                case null, default -> region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            }
        }

        StackPane.setAlignment(container, position);
        StackPane.setMargin(container, insets);

        if (!root.getChildren().contains(container)) {
            root.getChildren().add(container);
        }
        reset();

    }

    private void reset(Region target) {
        target.localToSceneTransformProperty().removeListener(move);
        reset();
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
    }

    @Override
    public void openLeft(Node container) {
        openLeft(container, Insets.EMPTY);
    }

    @Override
    public void openLeft(Node container, Insets insets) {
        openAbsolute(container, Pos.CENTER_LEFT, insets);
        if (container instanceof Region region)
            region.setMaxHeight(Region.USE_COMPUTED_SIZE);
    }

    @Override
    public void openRight(Node container) {
        openRight(container, Insets.EMPTY);
    }

    @Override
    public void openRight(Node container, Insets insets) {
        openAbsolute(container, Pos.CENTER_RIGHT, insets);
        if (container instanceof Region region)
            region.setMaxHeight(Region.USE_COMPUTED_SIZE);
    }

    @Override
    public void openByCursor(Region container, MouseEvent e) {
        openByCursor(container, e, Pos.CENTER, 0, 0);
    }

    public void openByCursor(Region container, MouseEvent e, Pos pos) {
        openByCursor(container, e, pos, 0, 0);
    }

    private double width = -1;
    private double height = -1;

    class UpdateBounds implements ChangeListener<Bounds> {

        private final Region container;
        private final MouseEvent event;
        private final Pos pos;
        private final double x;
        private final double y;

        public UpdateBounds(Region container, MouseEvent event, Pos pos, double x, double y) {
            this.container = container;
            this.event = event;
            this.pos = pos;
            this.x = x;
            this.y = y;
        }

        @Override
        public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
            // Needs to be reviewed
            // cause if the width is settled before the height given a 2x call
//            System.out.println("newValue = " + newValue.getHeight());
            if (newValue.getHeight() > 0) {
//            System.out.println("newValue = " + newValue);
                width = container.getWidth();
                height = container.getHeight();
                relocate(container, event, pos, x, y);
            }
        }
    }

    private UpdateBounds updateBounds;

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
    public void openByCursor(Region container, MouseEvent e, double x, double y) {
        if (root.getChildren().contains(container)) return;
        if (updateBounds != null) container.layoutBoundsProperty().removeListener(updateBounds);

        container.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        StackPane.clearConstraints(container);
        StackPane.setAlignment(container, Pos.TOP_LEFT);

//        container.getStyleClass().add("popup");

        container.setLayoutX(0);
        container.setLayoutY(0);

        // if lay out is not set
        if (width == 0 || height == 0) {
            updateBounds = new UpdateBounds(container, e, pos, x, y);
            container.layoutBoundsProperty().addListener(updateBounds);
//            container.widthProperty().addListener(updateBounds);
        } else { // if is set
            container.layoutBoundsProperty().removeListener(updateBounds);
            relocate(container, e, pos, x, y);
        }
//        root.getChildren().add(container);
        container.toFront();
    }

    @Override
    public void openByCursor(Region container, MouseEvent e, Pos pos, double x, double y) {
        if (root.getChildren().contains(container)) return;
        if (updateBounds != null) container.layoutBoundsProperty().removeListener(updateBounds);

        container.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        StackPane.clearConstraints(container);
        StackPane.setAlignment(container, Pos.TOP_LEFT);

//        container.getStyleClass().add("popup");
        container.setLayoutX(0);
        container.setLayoutY(0);

        // if layout is not set
        if (container.getWidth() == 0 || container.getHeight() == 0) {
            updateBounds = new UpdateBounds(container, e, pos, x, y);
            container.layoutBoundsProperty().addListener(updateBounds);
        } else { // if is set
            container.layoutBoundsProperty().removeListener(updateBounds);
            relocate(container, e, pos, x, y);
        }
        root.getChildren().add(container);
        container.toFront();
    }

    @ApiStatus.Experimental
    private void relocate(Region container, MouseEvent e, Pos pos, double x, double y) {

        container.setTranslateY(0);
        container.setTranslateX(0);

        container.setLayoutX(0);
        container.setLayoutY(0);

        double widthAvailable = root.getWidth();
        double heightAvailable = root.getHeight();

        double spaceXAvailable = widthAvailable - width;
        double spaceYAvailable = heightAvailable - height;

        // posX and posY <= 0 doesn't fit
        if (!fitWidth(spaceXAvailable + x) || !fitHeight(spaceYAvailable + y)) {
            if (root.getChildren().contains(container)) return;
            setLayout(container);
        } else {
            updateLayout(container, e, pos, x, y);
        }
    }

    @ApiStatus.Experimental
    private void setLayout(Region container) {
        clearConstraints(container);
    }

    @ApiStatus.Experimental
    private void updateLayout(@NotNull Region container, @NotNull MouseEvent event, @NotNull Pos pos, double offsetX, double offsetY) {
        VPos vPos = pos.getVpos();
        HPos hPos = pos.getHpos();

        double posX = getPosX(container.getWidth(), event.getSceneX(), hPos, offsetX);
        double posY = getPosY(container.getHeight(), event.getSceneY(), vPos, offsetY);

        double passX = 0;
        double passY = 0;

        if ((posX + width) > root.getWidth()) {
            passX = (posX + width) - root.getWidth();
        }
        if ((posY + height) > root.getHeight()) {
            passY = (posY + height) - root.getHeight();
        }
        container.setTranslateX(posX - passX);
        container.setTranslateY(posY - passY);
//        StackPane.setMargin(container, new Insets(posY - passY, 0, 0, posX - passX));
    }

    @Contract(pure = true)
    private double getPosX(double width, double mousePosX, @NotNull HPos hPos, double offsetX) {
        switch (hPos) {
            case LEFT -> {
                return (mousePosX - width) + offsetX;
            }
            case CENTER -> {
                return (mousePosX - (width / 2)) + offsetX;
            }
            default -> {
                return mousePosX + offsetX;
            }
        }
    }

    @Contract(pure = true)
    private double getPosY(double height, double mousePosY, @NotNull VPos vPos, double offsetY) {
        switch (vPos) {
            case CENTER -> {
                return (mousePosY - (height / 2)) + offsetY;
            }
            case TOP -> {
                return (mousePosY - height) + offsetY;
            }
            default -> {
                return mousePosY + offsetY;
            }
        }
    }

    private boolean fitWidth(double width) {
        return width >= 0;
    }

    private boolean fitHeight(double height) {
        return height >= 0;
    }

    @Override
    public void openByNode(Region container, Node node, Pos pos) {
        openByNode(container, node, pos, 0, 0);
    }

    public void openByNode(Region container, Node node, Pos pos, double x, double y) {
//        if (root.getChildren().contains(container)) return;
        container.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        StackPane.clearConstraints(container);
        StackPane.setAlignment(container, Pos.TOP_LEFT);

//        var updateBounds = new UpdateBounds(container);


//        container.parentProperty().addListener((observable, oldValue, newValue) -> {
//            Bounds bounds = node.localToScene(node.getLayoutBounds());
//            HPos hPos = pos.getHpos();
//            VPos vPos = pos.getVpos();
////
//            switch (hPos) {
//                case LEFT -> {
//                    container.setTranslateX(bounds.getMinX() - bounds.getWidth());
//                }
//                case RIGHT -> {
////                    container.setTranslateX(bounds.getMaxX());
//                    container.setTranslateX(bounds.getWidth() - bounds.getMinX());
//                }
////                case CENTER -> {
////                    container.setTranslateX( (bounds.getMaxX() - (bounds.getWidth() / 2)) - newValue.getCenterX()  );
////                }
//            }
////
//            container.setTranslateX(container.getTranslateX() + x);
//            switch (vPos) {
//                case TOP -> {
//                    container.setTranslateY(bounds.getMinY() - bounds.getHeight());
//                }
////                case CENTER -> {
////                    container.setTranslateY(bounds.getMaxY() - (bounds.getHeight() / 2) - newValue.getCenterY());
////                }
//                case BOTTOM -> {
//                    container.setTranslateY(bounds.getMaxY());
//                }
//            }
////
//            container.setTranslateY(container.getTranslateY() + y);
//
////            updateNodeBound(container, node, x, y);
//        });
//        container.toBack();

//        container.setTranslateY(bounds.getMaxY());
//        container.setTranslateX(bounds.getMaxX());
//        updateNodeBound(container, node, x, y);
        if (!root.getChildren().contains(container)) {
            root.getChildren().add(container);
        }
        container.toFront();
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
        this.content.setMaxWidth(width);
        this.content.setPrefWidth(width);
        this.content.setMinWidth(width);
        return this;
    }

    private double getWidth() {
        return width == -1 ? this.content.prefWidth(root.getWidth()) : width;
    }

    @Override
    public Flow height(double height) {
        this.height = height;
        return this;
    }

    private double getHeight() {
        return height == -1 ? this.content.prefHeight(root.getHeight()) : height;
    }

    private class Move implements ChangeListener<Transform> {

        private final Region target;
        private final Pos pos;

        public Move(Region target, Pos pos) {
            this.target = target;
            this.pos = pos;
        }

        @Override
        public void changed(ObservableValue<? extends Transform> observable, Transform oldValue, Transform newValue) {
            translateByTarget(pos, target, newValue.getTx(), newValue.getTy());
        }
    }

    ;

    private Move move;

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

    double x = 0;
    double y = 0;

    private void relocateByNode(Region target) {

        pos = Pos.BOTTOM_LEFT;
        double maxX = getMaxPositionX(pos.getHpos(), target);
        double maxY = getMaxPositionY(pos.getVpos(), target);

        double spaceAvailableWidth = root.getWidth() - maxX;
        double spaceAvailableHeight = root.getHeight() - maxY;

        double height = this.height == -1 ?
                this.content.prefHeight(-1) : this.height;

        this.content.setMaxHeight(height);
        this.content.setPrefHeight(height);
        this.content.setMinHeight(height);

        double width = this.width == -1 ?
                this.content.prefWidth(-1) : this.width;

        this.content.setMaxWidth(width);
        this.content.setPrefWidth(width);
        this.content.setMinWidth(width);

//        this.content.requestLayout();
//        this.content.requestFocus();
//        Platform.requestNextPulse();

        switch (pos.getHpos()) {
            case LEFT -> x = maxX - width;
            case RIGHT -> x = maxX;
            case null, default -> x = maxX - (width / 2);
        }

        switch (pos.getVpos()) {
            case BOTTOM -> y = maxY;
            case TOP -> y = maxY - height;
            case null, default -> y = maxY - (height / 2);
        }
        content.setTranslateX(Math.round(x));
        content.setTranslateY(Math.round(y));
    }

    private void show2() {

    }

    //    private double snapSpaceX(double value, boolean snapToPixel) {
//        return snapToPixel ? ScaledMath.round(value, getSnapScaleX()) : value;
//    }
//    private double snapSpaceY(double value, boolean snapToPixel) {
//        return snapToPixel ? ScaledMath.round(value, getSnapScaleY()) : value;
//    }
    double computeChildMinAreaWidth(Node child, double baselineComplement, Insets margin, double height, boolean fillHeight) {
        final boolean snap = this.content.isSnapToPixel();
        double left = margin != null ? margin.getLeft() : 0;
        double right = margin != null ? margin.getRight() : 0;
        double alt = -1;
        if (height != -1 && child.isResizable() && child.getContentBias() == Orientation.VERTICAL) { // width depends on height
            double top = margin != null ? margin.getTop() : 0;
            double bottom = (margin != null ? margin.getBottom() : 0);
            double bo = child.getBaselineOffset();
            final double contentHeight = bo == BASELINE_OFFSET_SAME_AS_HEIGHT && baselineComplement != -1 ?
                    height - top - bottom - baselineComplement :
                    height - top - bottom;
            if (fillHeight) {
                alt = boundedSize(
                        child.minHeight(-1), contentHeight,
                        child.maxHeight(-1));
            } else {
                alt = boundedSize(
                        child.minHeight(-1),
                        child.prefHeight(-1),
                        Math.min(child.maxHeight(-1), contentHeight));
            }
        }
        return left + child.minWidth(alt) + right;
    }

    protected double computePrefWidth(double height) {
        double minX = 0;
        double maxX = 0;
        for (int i = 0, max = ((Pane) this.content).getChildren().size(); i < max; i++) {
            Node node = ((Pane) this.content).getChildren().get(i);
            if (node.isManaged()) {
                final double x = node.getLayoutBounds().getMinX() + node.getLayoutX();
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x + boundedSize(node.prefWidth(-1), node.minWidth(-1), node.maxWidth(-1)));
            }
        }
        return maxX - minX;
    }

    double boundedSize(double value, double min, double max) {
        // if max < value, return max
        // if min > value, return min
        // if min > max, return min
        return Math.min(Math.max(value, min), Math.max(min, max));
    }

    private double computePrefHeight(double width) {
        double minY = 0;
        double maxY = 0;
        for (int i = 0, max = ((Pane) this.content).getChildren().size(); i < max; i++) {
            Node node = ((Pane) this.content).getChildren().get(i);
            if (node.isManaged()) {
                final double y = node.getLayoutBounds().getMinY() + node.getLayoutY();
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y + boundedSize(node.prefHeight(-1), node.minHeight(-1), node.maxHeight(-1)));
            }
        }
        System.out.println("maxY = " + maxY);
        System.out.println("minY = " + minY);
        System.out.println("height = " + (maxY - minY));
        return maxY - minY;
    }

    private double getMaxPositionY(VPos pos, Region target) {
        System.out.println("vpos = " + pos);
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
        System.out.println("hpos = " + pos);
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
        content.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//        var innerPos = pos;
//
//        move = new Move(target, innerPos);
//        target.localToSceneTransformProperty().addListener(move);
//
//        translate(pos, target, target.getLocalToSceneTransform().getTx(), target.getLocalToSceneTransform().getTy());
//        content.setTranslateX(event.getSceneX());
//        content.setTranslateY(event.getSceneY());

        StackPane.setMargin(content, insets);

        if (!root.getChildren().contains(content)) {
            root.getChildren().add(content);
        }
        Bounds bounds = content.localToParent(content.getLayoutBounds());

        translateByCursor(pos, content, event.getSceneX(), event.getSceneY());
        reset();
    }

    private void translateByCursor(Pos pos, Region target, double tx, double ty) {

        System.out.println("pos = " + pos);
        System.out.println("target.getPrefHeight() = " + target.getPrefHeight());
        System.out.println("target.getPrefHeight() = " + target.getMinHeight());
        System.out.println("target.getPrefHeight() = " + target.getHeight());
        System.out.println("target.getPrefHeight() = " + target.getBoundsInLocal());
        System.out.println("target.getPrefHeight() = " + target.getLayoutBounds());
        System.out.println("target.getPrefHeight() = " + target.getBoundsInParent());
        System.out.println("target.getPrefHeight() = " + target.getLocalToParentTransform());


        switch (pos.getVpos()) {
            case TOP -> content.setTranslateY(ty - target.getHeight());
            case BOTTOM -> content.setTranslateY(ty + target.getHeight());
            default -> content.setTranslateY((ty - (content.getHeight() / 2)) + (target.getHeight() / 2));
        }
        switch (pos.getHpos()) {
            case LEFT -> content.setTranslateX(tx - target.getWidth());
            case RIGHT -> content.setTranslateX(tx + target.getWidth());
            case CENTER -> content.setTranslateX((tx - (content.getWidth() / 2)) + (target.getWidth() / 2));
        }
    }

    private void translateByTarget(Pos pos, Region target, double tx, double ty) {

        System.out.println("target.getBoundsInLocal().getWidth() = " + target.getBoundsInLocal().getWidth());
        System.out.println("target.getBoundsInLocal().getWidth() = " + target.getPrefWidth());


        switch (pos.getVpos()) {
//            case TOP -> content.setTranslateY(ty - target.getBoundsInLocal().getHeight());
//            case BOTTOM -> content.setTranslateY(ty + target.getBoundsInLocal().getHeight());
//            default -> content.setTranslateY((ty - (content.getHeight() / 2)) + target.getBoundsInLocal().getCenterY());
        }
        switch (pos.getHpos()) {
//            case LEFT -> content.setTranslateX(tx - target.getBoundsInLocal().getWidth());
            case RIGHT -> content.setTranslateX((tx + target.getBoundsInLocal().getWidth()) - content.getPrefWidth());
//            case CENTER -> content.setTranslateX((tx - (content.getWidth() / 2)) + target.getBoundsInLocal().getCenterX());
        }
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
