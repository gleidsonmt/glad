package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.dialog.DialogContainer;
import io.github.gleidsonmt.glad.dialog.alert.layout.AlertRoot;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class AlertImpl extends DialogAbstract<Alert> implements Alert {

    private Root root;
    private AlertRoot alert;

    private AlertType type = AlertType.INFO;
    private Button[] buttons;

    private Snack snack;

    public AlertImpl(Root root) {
        this.root = root;
    }

    private void open(String title) {
        open(title, AlertType.INFO);
    }

    private void open(String title, AlertType alertType) {
        open(title, null, alertType);
    }

    private void open(String title, Node node, AlertType alertType, Button... buttons) {
        alert = new AlertRoot(title, node, alertType);
        if (node == null) {
            throw new RuntimeException("Error alert can invoke a null node.");
        }
//
        if (buttons == null || buttons.length == 0) {
            Button ok = new Button("Ok");
            ButtonBar.setButtonData(ok, ButtonBar.ButtonData.OK_DONE);
            ok.setOnAction(e -> root.behavior().alert().hide());
            alert.getButtonBar().getButtons().setAll(ok);
        } else {
            alert.getButtonBar().getButtons().setAll(buttons);
            Arrays.stream(buttons).forEach(button -> {
                if (button.getText().equalsIgnoreCase("ok")) {
                    button.setDefaultButton(true);
                }
                if (button.getText().equalsIgnoreCase("cancel")) {
                    button.setCancelButton(true);
                }

                button.addEventFilter(ActionEvent.ACTION, _-> root.behavior().alert().hide());
            });

        }

        root.wrapper().show(WrapperEffect.GRAY);

        root.flow()
                .pos(Pos.CENTER)
                .width(width != -1 ? width : 600)
                .height(height != -1 ? height : 300)
                .content(new DialogContainer(alert))
                .show();

        reset();
    }

    private void reset() {
        this.wrapperEffect = null;
        this.buttons = null;
    }

    @Override
    public void hide() {
        root.flow().remove(alert.getParent());
        root.wrapper().hide();
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
    public Alert type(String type) {
        this.type = AlertType.valueOf(type.toUpperCase());
        return this;
    }
//
//    @Override
//    public Alert effect(WrapperEffect effect) {
//        this.effect = effect;
//        return this;
//    }

    @Override
    public void show() {
        open(title, content, type, buttons);
    }


}
