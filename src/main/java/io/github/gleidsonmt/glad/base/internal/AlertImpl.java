package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.dialog.alert.layout.AlertRoot;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import io.github.gleidsonmt.glad.base.Alert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class AlertImpl implements Alert {

    private Root root;
    private AlertRoot alert;

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
        alert.setContent(node);
        root.wrapper().show();
        if (buttons.length  == 0) {
            Button ok = new Button("Ok");
            ButtonBar.setButtonData(ok, ButtonBar.ButtonData.OK_DONE);
            ok.setOnAction(e -> root.behavior().alert().close());
            alert.getButtonBar().getButtons().addAll(ok);
        } else {
            alert.getButtonBar().getButtons().addAll(buttons);
        }

        root.flow().openAbsolute(alert, Pos.CENTER, Insets.EMPTY);

    }

    @Override
    public void close() {
        root.flow().remove(alert);
    }
}
