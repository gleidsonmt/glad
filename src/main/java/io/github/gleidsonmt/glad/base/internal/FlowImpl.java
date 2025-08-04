package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import io.github.gleidsonmt.glad.base.internal.animations.Anchor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class FlowImpl implements Flow {

    private Pos pos = Pos.CENTER;
    private Insets insets = Insets.EMPTY;
    private Anchor anchor = null;
    private Node content;
    private final Root root;

    public FlowImpl(Root root) {
        this.root = root;
    }

    @Override
    public void openAbsolute(Node container) {
        openAbsolute(container, Pos.CENTER, Insets.EMPTY);
    }


    @Override
    public void openAbsolute(Node container, Pos pos) {
        openAbsolute(container, pos, Insets.EMPTY);
    }

    @Override
    public void openAbsolute(Node container, Pos position, Insets insets) {

        StackPane.clearConstraints(container);

        if (container instanceof Region region) {
            if (anchor != null) {
                switch (anchor) {
                    case TOP, BOTTOM -> region.setMaxHeight(Region.USE_PREF_SIZE);
                    case LEFT, RIGHT -> region.setMaxWidth(Region.USE_PREF_SIZE);
                }
            } else {
                region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            }

            StackPane.setAlignment(container, position);
            StackPane.setMargin(container, insets);

            if (!root.getChildren().contains(container)) {
                root.getChildren().add(container);
            }
        }
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

    private Layout oldLayout = null;

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

//        this.oldLayout = root.getLayout();
//        container.getStyleClass().add("popup");
//        root.setLayout(new Layout(container));
//        root.wrapper().back();

//        root.widthProperty().addListener(new ChangeListener<>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                root.setLayout(oldLayout);
//                root.widthProperty().removeListener(this);
//            }
//        });
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


        container.parentProperty().addListener((observable, oldValue, newValue) -> {
            Bounds bounds = node.localToScene(node.getLayoutBounds());
            HPos hPos = pos.getHpos();
            VPos vPos = pos.getVpos();
//
            switch (hPos) {
                case LEFT -> {
                    container.setTranslateX(bounds.getMinX() - bounds.getWidth());
                }
                case RIGHT -> {
//                    container.setTranslateX(bounds.getMaxX());
                    container.setTranslateX(bounds.getWidth() - bounds.getMinX());
                }
//                case CENTER -> {
//                    container.setTranslateX( (bounds.getMaxX() - (bounds.getWidth() / 2)) - newValue.getCenterX()  );
//                }
            }
//
            container.setTranslateX(container.getTranslateX() + x);
            switch (vPos) {
                case TOP -> {
                    container.setTranslateY(bounds.getMinY() - bounds.getHeight());
                }
//                case CENTER -> {
//                    container.setTranslateY(bounds.getMaxY() - (bounds.getHeight() / 2) - newValue.getCenterY());
//                }
                case BOTTOM -> {
                    container.setTranslateY(bounds.getMaxY());
                }
            }
//
            container.setTranslateY(container.getTranslateY() + y);

//            updateNodeBound(container, node, x, y);
        });
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
    public Flow with(WrapperEffect effect) {
        root.wrapper().show(effect);
        return this;
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
    public Flow anchor(Anchor anchor) {
        this.anchor = anchor;
        return this;
    }

    @Override
    public Flow insets(Insets insets) {
        this.insets = insets;
        return this;
    }

    @Override
    public Flow pos(Pos pos) {
        this.pos = pos;
        return this;
    }

    public Flow content(Node content) {
        this.content = content;
        return this;
    }

    @Override
    public void show() {
        if (content == null) {
            throw new RuntimeException("Error flow can invoke a null node.");
        }
        openAbsolute(content, pos, insets);
    }
}
