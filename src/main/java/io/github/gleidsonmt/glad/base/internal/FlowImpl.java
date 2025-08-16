package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.controls.button.Button;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Transform;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


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

    private double width = 0;
    private double height = 0;

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
        width(-1, -1, width);
        return this;
    }

    @Override
    public Flow width(double width, double min) {
        width(-1, min, width);
        return null;
    }

    @Override
    public Flow width(double width, double min, double max) {
        this.content.setMaxWidth(max);
        this.content.setPrefWidth(width);
        this.content.setMinWidth(min);
        return this;
    }

    private double getWidth() {
        if (this.content.getMaxWidth() != -1) {
            return this.content.getMaxWidth();
        }
        if (this.content.getPrefWidth() != -1) {
            return this.content.getPrefWidth();
        }
        if (this.content.getMinWidth() != -1) {
            return this.content.getMinWidth();
        }

        return this.content.prefWidth(root.getWidth());

    }

    @Override
    public Flow height(double height) {
        height(-1,-1, height);
        return this;
    }

    @Override
    public Flow height(double height, double min) {
        height(-1, min, height);
        return this;
    }

    @Override
    public Flow height(double height, double min, double max) {
        this.content.setMaxHeight(max);
        this.content.setPrefHeight(height);
        this.content.setMinHeight(min);
        return this;
    }

    private double getHeight() {
        if (this.content.getMaxHeight() != -1) {
            return this.content.getMaxHeight();
        }
        if (this.content.getPrefHeight() != -1) {
            return this.content.getPrefHeight();
        }
        if (this.content.getMinHeight() != -1) {
            return this.content.getMinHeight();
        }

        return this.content.prefHeight(root.getHeight());

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
//        content.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        var innerPos = pos;

//        move = new Move(target, innerPos);
//        target.localToSceneTransformProperty().addListener(move);

//        translateByTarget(pos, target, target.getLocalToSceneTransform().getTx(), target.getLocalToSceneTransform().getTy());
//        content.setPrefWidth(500);
//        content.setMaxWidth(500);

        content.setMaxWidth(getWidth());
        content.setMaxHeight(getHeight());

        relocateByNode(target, content.getMaxWidth(), content.getMaxHeight() );

        if (!root.getChildren().contains(content)) {
            root.getChildren().add(content);
        }
        StackPane.setMargin(content, insets);
        reset();
    }

    private void relocateByNode(Region target, double width, double height) {
        content.setPickOnBounds(true);

        double spaceWidthNeed = target.getLocalToSceneTransform().getTx() + width ;
        double spaceHeightNeed = target.getLocalToSceneTransform().getTx() + height;

        double spaceWidthAvailable = root.getWidth() - target.getLocalToSceneTransform().getTx();
        double spaceHeightAvailable = root.getHeight() - target.getLocalToSceneTransform().getTy();

//        double spaceWidthAvailable = spaceWidthNeed > root.getWidth() ? root.getWidth() - width : spaceWidth ;
//        double spaceHeightAvailable = spaceHeight > root.getHeight() ? root.getHeight() : spaceHeight ;
        System.out.println("(spaceWidthNeed > root.getWidth()) = " + (spaceWidthNeed > root.getWidth()));

        double x = 0;
        double y = 0;

        switch (pos.getHpos()) {
            case RIGHT -> {
                 x =   width <= spaceWidthAvailable ?
                         target.getLocalToSceneTransform().getTx() + target.getWidth()
                         : root.getWidth() - width ;
            }
            case LEFT -> {
                x =  width <= spaceWidthAvailable ?
                        target.getLocalToSceneTransform().getTx() - width  : root.getWidth() - width;
            }
            case null, default -> {
                boolean contain = width / 2 <= spaceWidthAvailable;
                double totalMoveX = (target.getLocalToSceneTransform().getTx() - (width / 2)) + (target.getWidth() / 2);

                x = contain ? // se possui espaco para colocar o node
                        totalMoveX // entao calculo
                        :
                        totalMoveX - // actual move x
                            ((target.getLocalToSceneTransform().getTx() + (width/2) ) // todo o comprimento excendedo o width do root
                             - root.getWidth()); // tirando a diferenca
            }
        }

        switch (pos.getVpos()) {
            case BOTTOM -> {
                y =  height <= spaceHeightAvailable  ?
                        target.getLocalToSceneTransform().getTy() + target.getHeight() : root.getHeight() - height ;
            }
            case TOP -> {
                y = spaceHeightNeed > spaceHeightAvailable ?
                        target.getLocalToSceneTransform().getTy() - height : root.getHeight() - height;
            }
            case null, default -> {
                spaceHeightAvailable = target.getLocalToSceneTransform().getTy() - ;
                boolean contain = height / 2 <= spaceHeightAvailable;
                double totalMoveY = (target.getLocalToSceneTransform().getTy() - (height / 2)) + (target.getHeight() / 2);
                System.out.println("contain = " + contain);
                y = contain ?
                        totalMoveY
                        :
                        totalMoveY + // actual move x
                        ((target.getLocalToSceneTransform().getTy() + (height/2) ) // todo o comprimento excendedo o width do root
                         - root.getHeight()); // tirando a diferenca
            }
        }
        content.setTranslateX(Math.round(x));
        content.setTranslateY(Math.round(y));
//
//        content.setTranslateX(x);
//        content.setTranslateY(y);
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
