

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.Alert;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.dialog.alert.AlertType;
import io.github.gleidsonmt.glad.base.dialog.alert.layout.AlertLayout;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  21/03/2025
 */
public class AlertImpl extends DialogAbstract<Alert> implements Alert {

    private AlertLayout alert;

    private AlertType type = AlertType.INFO;
    private Button[] buttons;

    public AlertImpl(Root root) {
        super(root);
    }

    private void open(String title) {
        open(title, AlertType.INFO);
    }

    private void open(String title, AlertType alertType) {
        open(title, null, alertType);
    }

    private void open(String title, Node node, AlertType alertType, Button... buttons) {
        alert = new AlertLayout(title, node, alertType);

        if (node == null) {
            throw new RuntimeException("Error alert can invoke a null node.");
        }

        if (buttons == null || buttons.length == 0) {
            Button ok = new Button("Ok");
            ButtonBar.setButtonData(ok, ButtonBar.ButtonData.OK_DONE);
            ok.setOnAction(_ -> root.behavior().alert().hide());
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

                button.addEventFilter(ActionEvent.ACTION, _ -> root.behavior().alert().hide());
            });
        }

        root.getChildren().add(foreground.restyle(wrapperEffect == null ? WrapperEffect.GRAY : wrapperEffect, root));

        root.flow()
                .pos(Pos.CENTER)
                .width(width.get() != -1 ? width.get() : 600)
                .height(height.get() != -1 ? height.get() : 300)
                .content(new DialogContainer(alert))
                .show();

        System.out.println("this.block = " + this.block);
        if (this.block) blockForeground();
//        System.out.println("this.block = " + this.block);

//
    }

    private void reset() {
        this.wrapperEffect = null;
        this.buttons = null;
        this.block = false;
    }

    @Override
    public void hide() {
        root.flow().remove(alert.getParent());
        root.flow().remove(foreground);
        reset();
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
