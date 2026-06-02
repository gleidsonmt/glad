

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
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
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
    private final Drawer drawer;

    private final BooleanProperty drawerOpen = new SimpleBooleanProperty();

    public BehaviorImpl(Root root) {
        this.root = root;
        drawerTimeline = new Timeline();
        asideTimeline = new Timeline();

        this.alert = new AlertImpl(root);
        this.dialog = new DialogImpl(root);
        this.snack = new SnackImpl(root);
        this.drawer = new DrawerImpl(root);
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

    @Override
    public Drawer drawer() {
        return this.drawer;
    }
}
