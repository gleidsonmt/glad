package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Behavior;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.Size;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
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

    private  Timeline drawerTimeline;
    private  Timeline asideTimeline;

    private BooleanProperty drawerOpen = new SimpleBooleanProperty();

//    private Size size;
    private ObjectProperty<Size> size = new SimpleObjectProperty<>();


    public BehaviorImpl(Root root, Container container) {
        this.root = root;
        this.container = container;
        drawerTimeline = new Timeline();
        asideTimeline = new Timeline();

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
            if (size.get() == null || size.get() != act) {
                size.set(act);
                _switch(act);
            }
        });

    }

    private void _switch(Size size) {
        switch (size) {
            case TABLET -> {
                root.flow().remove(drawer);
                root.flow().remove(aside);
                root.wrapper().close();
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

    @Override
    public void openAside() {
        if (!isAsideOpen()) {
            asideTimeline.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(aside.translateXProperty(), 250)),
                    new KeyFrame(Duration.millis(200), new KeyValue(aside.translateXProperty(), 0))
            );
            asideTimeline.setOnFinished(null);
            asideTimeline.setRate(1);
            root.wrapper().show();
            root.flow().openRight(aside);
            asideTimeline.play();
        }
    }

    @Override
    public void closeAside() {
        if (isAsideOpen()) {
            asideTimeline.setRate(-1);
            root.wrapper().close();
            asideTimeline.setOnFinished(e -> {
                root.flow().remove(aside);
                aside.setTranslateX(0);
            });
            asideTimeline.play();
        }
    }

    @Override
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

    @Override
    public boolean isDrawerOpen() {
        return container.getLeft() != null || isDrawerAbsolute();
    }

    public boolean isAsideOpen() {
        return container.getRight() != null || isAsideAbsolute();
    }

    private boolean isAsideAbsolute() {
        return this.root.getChildren().contains(aside);
    }

    public BooleanProperty drawerOpen() {
        return this.drawerOpen;
    }

    public boolean isDrawerAbsolute() {
        return this.root.getChildren().contains(drawer);
    }

    private Size getSize(double width) {
        if (width <= root.getBreakpoint()) return Size.PHONE;
        else return Size.TABLET;
    }

    public ObjectProperty<Size> sizeProperty() {
        return this.size;
    }
}
