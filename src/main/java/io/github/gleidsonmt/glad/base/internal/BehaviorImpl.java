package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Alert;
import io.github.gleidsonmt.glad.base.Behavior;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.animations.Anchor;
import io.github.gleidsonmt.glad.dialog.Dialog;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/01/2025
 */
public class BehaviorImpl implements Behavior {

    private Root root;

    private Node drawer;
    private Node aside;

    private Timeline drawerTimeline;
    private Timeline asideTimeline;

    private Alert alert;
    private Dialog dialog;


    private BooleanProperty drawerOpen = new SimpleBooleanProperty();

    public BehaviorImpl(Root root) {
        this.root = root;
        drawerTimeline = new Timeline();
        asideTimeline = new Timeline();

        this.alert = new AlertImpl(root);
        this.dialog = new DialogImpl(root);

//        if (layout.getLeft() != null) drawer = layout.getLeft();
//        if (layout.getRight() != null) aside = layout.getRight();
//
//        layout.leftProperty().addListener((observableValue, node, newValue) -> {
//            if (newValue != null) {
//                drawer = newValue;
//            }
//        });

//        layout.rightProperty().addListener((observableValue, node, newValue) -> {
//            if (newValue != null) {
//                aside = newValue;
//            }
//        });

//        root.widthProperty().addListener((observableValue, number, newValue) -> {
//            Size act = getSize(newValue.doubleValue());
//            if (size.get() == null || size.get() != act) {
//                size.set(act);
//                _switch(act);
//            }
//        });

    }

    @Override
    public void setDrawer(Node drawer) {
        System.out.println("drawer = " + drawer);
        this.drawer = drawer;
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

            root.flow()
                    .pos(Pos.CENTER_LEFT)
                    .content(drawer)
                    .anchor(Anchor.LEFT)
                    .insets(Insets.EMPTY)
                    .show();

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
            root.wrapper().hide();
            asideTimeline.setOnFinished(e -> {
                root.flow().remove(aside);
                if (aside != null) {
                    aside.setTranslateX(0);
                }
            });
            asideTimeline.play();
        }
    }

    @Override
    public void closeDrawer() {
        if (isDrawerOpen()) {
            drawerTimeline.setRate(-1);
            drawerTimeline.setOnFinished(e -> {
                root.wrapper().hide();
                root.flow().remove(drawer);
                if (drawer != null) {
                    drawer.setTranslateX(0);
                }
            });
            drawerTimeline.play();
        }
    }

    @Override
    public Alert alert() {
        return this.alert;
    }

    @Override
    public Dialog dialog() {
        return this.dialog;
    }

    @Override
    public boolean isDrawerOpen() {
        return root.getLayout().getDrawer() != null || isDrawerAbsolute();
//        return isDrawerAbsolute();
    }

    public boolean isAsideOpen() {
//        return layout.getRight() != null || isAsideAbsolute();
        return true;
    }

    private boolean isAsideAbsolute() {
        return this.root.getChildren().contains(aside);
    }

    public BooleanProperty drawerOpen() {
        return this.drawerOpen;
    }

    @Override
    public boolean isDrawerAbsolute() {
        return this.root.getChildren().contains(drawer);
    }

//    private Size getSize(double width) {
//        if (width <= root.getBreakpoint()) return Size.PHONE;
//        else return Size.TABLET;
//    }


}
