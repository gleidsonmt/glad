package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Behavior;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/01/2025
 */
public class BehaviorImpl implements Behavior {

    private  Root root;
    private  Container container;

    private Node drawer;
    private Node aside;
    private Size size;
    private  Timeline drawerTimeline;

    private enum Size {
        PHONE, TABLET
    }

    public BehaviorImpl(Root root, Container container) {
        this.root = root;
        this.container = container;
        drawerTimeline = new Timeline();

        if (container.getLeft() != null) drawer = container.getLeft();
        if (container.getRight() != null) aside = container.getRight();

        container.leftProperty().addListener((observableValue, node, newValue) -> {
            if (newValue != null) {
                drawer = newValue;
            }
        });

        container.rightProperty().addListener((observableValue, node, newValue) -> {
            if (newValue != null) {
                aside = newValue;
            }
        });

        root.widthProperty().addListener((observableValue, number, newValue) -> {
            Size act = getSize(newValue.doubleValue());
            if (size == null || size != act) {
                size = act;
                _switch(size);
            }
        });
    }

    private void _switch(Size size) {
        switch (size) {
            case TABLET -> {
                root.flow().remove(drawer);
//                root.flow().remove(aside);
//                root.wrapper().close();
                container.setLeft(drawer);
                container.setRight(aside);
            }
            case PHONE -> {
                container.setLeft(null);
                container.setRight(null);
            }
        }
    }

    @Override
    public void openDrawer() {
        if (!isDrawerOpen()) {
            drawerTimeline.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(drawer.translateXProperty(), -250)),
                    new KeyFrame(Duration.millis(200), new KeyValue(drawer.translateXProperty(), 0))
            );
            drawerTimeline.setOnFinished(null);
            drawerTimeline.setRate(1);
            root.wrapper().show();
            root.flow().openLeft(drawer);
            drawerTimeline.play();
        }
    }

    public void closeDrawer() {
        if (isDrawerOpen()) {
            drawerTimeline.setRate(-1);
            root.wrapper().close();
            drawerTimeline.setOnFinished(e -> {
                root.flow().remove(drawer);
                drawer.setTranslateX(0);
            });
            drawerTimeline.play();
        }
    }

    public boolean isDrawerOpen() {
        return container.getLeft() != null || isDrawerAbsolute();
    }

    public boolean isDrawerAbsolute() {
        return this.root.getChildren().contains(drawer);
    }

    private Size getSize(double width) {
        if (width <= 640) return Size.PHONE;
        else return Size.TABLET;
    }

}
