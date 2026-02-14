package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.base.dialog.Alert;
import io.github.gleidsonmt.glad.base.dialog.Dialog;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.dialog.snack.Snack;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/01/2025
 */
public class BehaviorImpl implements Behavior {

    private final Root root;

    private Node aside;

    private final Timeline drawerTimeline;
    private final Timeline asideTimeline;

    private final Alert alert;
    private final Dialog dialog;
    private final Snack snack;

    private final BooleanProperty drawerOpen = new SimpleBooleanProperty();

    public BehaviorImpl(Root root) {
        this.root = root;
        drawerTimeline = new Timeline();
        asideTimeline = new Timeline();

        this.alert = new AlertImpl(root);
        this.dialog = new DialogImpl(root);
        this.snack = new SnackImpl(root);
    }

    @Deprecated(forRemoval = true)
    @Override
    public void openDrawer() {
        if (!isDrawerOpen()) {
            root.behavior()
                    .dialog()
                    .pos(Pos.CENTER_LEFT)
                    .effect(WrapperEffect.GRAY)
//                    .content(root.getLayout().getDrawer())
                    .anchor(Anchor.LEFT)
                    .insets(Insets.EMPTY)
                    .width(250)
                    .show();

            drawerTimeline.getKeyFrames().setAll(
//                    new KeyFrame(Duration.ZERO, new KeyValue(root.getLayout().getDrawer().getParent().translateXProperty(), -250)),
//                    new KeyFrame(Duration.millis(200), new KeyValue(root.getLayout().getDrawer().getParent().translateXProperty(), 0))
            );
            drawerTimeline.setOnFinished(null);
            drawerTimeline.setRate(1);

            drawerTimeline.play();
        }
    }

    @Deprecated(forRemoval = true)
    @Override
    public void openAside() {
        if (!isAsideOpen()) {
            asideTimeline.getKeyFrames().setAll(
//                    new KeyFrame(Duration.ZERO, new KeyValue(root.getLayout().getAside().translateXProperty(), 250)),
//                    new KeyFrame(Duration.millis(200), new KeyValue(root.getLayout().getAside().translateXProperty(), 0))
            );
            asideTimeline.setOnFinished(null);
            asideTimeline.setRate(1);
            root.behavior()
                    .dialog()
                    .pos(Pos.CENTER_RIGHT)
//                    .content(root.getLayout().getAside())
                    .anchor(Anchor.RIGHT)
                    .insets(Insets.EMPTY)
                    .width(400)
                    .effect()
                    .show();
            asideTimeline.play();
        }
    }

    @Deprecated(forRemoval = true)
    @Override
    public void closeAside() {
        if (isAsideOpen()) {
            asideTimeline.setRate(-1);
            root.wrapper().hide();
//            root.behavior().dialog().hide();
            asideTimeline.setOnFinished(e -> {
                root.wrapper().hide();
//                root.flow().remove(root.getLayout().getAside());
//                if (root.getLayout().getAside() != null) {
//                    root.getLayout().getAside().setTranslateX(0);
//                }
            });
            asideTimeline.play();
        }
    }

    @Deprecated(forRemoval = true)
    @Override
    public void closeDrawer() {
        if (isDrawerAbsolute()) {
            drawerTimeline.setRate(-1);
            drawerTimeline.setOnFinished(_ -> {
                root.wrapper().hide();
//                root.flow().remove(root.getLayout().getDrawer());
//                if (root.getLayout().getDrawer() != null) {
//                    root.getLayout().getDrawer().setTranslateX(0);
//                }
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
    public Snack snack() {
        return this.snack;
    }

    @Deprecated(forRemoval = true)
    @Override
    public boolean isDrawerOpen() {
        return isDrawerContained() || isDrawerAbsolute();
    }

    @Deprecated(forRemoval = true)
    private boolean isDrawerContained() {
        if (root.getLayout() instanceof Pane pane) {
//            return pane.getChildren().contains(root.getLayout().getDrawer());
        }
        return false;
    }

    @Deprecated(forRemoval = true)
    private boolean isAsideContained() {
        if (root.getLayout() instanceof Pane pane) {
//            return pane.getChildren().contains(root.getLayout().getAside());
        }
        return false;
    }

    @Deprecated(forRemoval = true)
    public boolean isAsideOpen() {
        return isAsideContained() || isAsideAbsolute();
    }

    @Deprecated(forRemoval = true)
    private boolean isAsideAbsolute() {
        return this.root.getChildren().contains(aside);
    }

    @Deprecated(forRemoval = true)
    public BooleanProperty drawerOpen() {
        return this.drawerOpen;
    }

    @Deprecated(forRemoval = true)
    @Override
    public boolean isDrawerAbsolute() {
//        return this.root.getChildren().contains(root.getLayout().getDrawer());
        return false;
    }

}
