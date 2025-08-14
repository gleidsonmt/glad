package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.dialog.alert.layout.AlertRoot;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class AlertImpl extends FlowItemAbstract<Alert> implements Alert {

    private Root root;
    private AlertRoot alert;
    private SnackBar snackBar;

    private AlertType type;
    private String title;
    private Button[] buttons;

    private Snack snack;

    public AlertImpl(Root root) {
        this.root = root;
    }

    @Override
    public void open(String title) {
         open(title, AlertType.INFO);
    }

    @Override
    public void open(String title, AlertType alertType) {
        open(title, null, alertType);
    }

    @Override
    public void open(String title, Node node, AlertType alertType, Button... buttons) {
        alert = new AlertRoot(alertType);
        alert.setTitle(title);
        if (node == null) {
            throw new RuntimeException("Error alert can invoke a null node.");
        }
        alert.setContent(node);
        root.wrapper().show(this.effect);

        if (buttons == null || buttons.length  == 0) {
            Button ok = new Button("Ok");
            ButtonBar.setButtonData(ok, ButtonBar.ButtonData.OK_DONE);
            ok.setOnAction(e -> root.behavior().alert().hide());
            alert.getButtonBar().getButtons().addAll(ok);
        } else {
            alert.getButtonBar().getButtons().addAll(buttons);
        }

        root.flow().openAbsolute(alert, Pos.CENTER, Insets.EMPTY);
        reset();
    }

    private void reset() {
        this.effect = WrapperEffect.GRAY;
    }

    @Override
    public void hide() {
        root.flow().remove(alert);
        root.wrapper().hide();
    }

    @Override
    public Snack snack(String message) {
        this.snack = new SnackImpl(this.root, message);
        return snack;
    }

    @Override
    public Alert title(String title) {
        this.title = title;
        return this;
    }


    @Override
    public Alert buttons(Button... buttons) {
        this.buttons = buttons;
        return this;
    }

    @Override
    public Alert type(AlertType type) {
        this.type = type;
        return this;
    }

    @Override
    public Alert effect(WrapperEffect effect) {
        this.effect = effect;
        return this;
    }

    @Override
    public void show() {
        open(title, super.content, type, buttons);
    }


}
