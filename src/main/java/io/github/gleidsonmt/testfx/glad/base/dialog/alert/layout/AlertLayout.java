

package io.github.gleidsonmt.testfx.glad.base.dialog.alert.layout;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.base.dialog.alert.AlertType;
import io.github.gleidsonmt.glad.base.dialog.alert.icons.ErrorIcon;
import io.github.gleidsonmt.glad.base.dialog.alert.icons.InfoIcon;
import io.github.gleidsonmt.glad.base.dialog.alert.icons.SuccessIcon;
import io.github.gleidsonmt.glad.base.dialog.alert.icons.WarnIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  06/11/2024
 */
public class AlertLayout extends VBox {

    private final BarAction barAction;

    public AlertLayout(String _title, Node node, AlertType type) {
        Node icon;
        Text title = new Text(_title);
        title.getStyleClass().addAll("h3 font-instagram-headline".split(" "));
        this.barAction = new BarAction();
        VBox barContainer = new VBox(barAction);
        barContainer.getStyleClass().add("bar-container");
        this.getStyleClass().add("alert");
        setAlignment(Pos.CENTER);
        VBox.setVgrow(barContainer, Priority.ALWAYS);

        switch (type) {
            case ERROR -> {
                this.getStyleClass().add("alert-error");
                icon = new ErrorIcon();
            }

            case SUCCESS -> {
                this.getStyleClass().add("alert-success");
                icon = new SuccessIcon();
            }
            case WARNING -> {
                this.getStyleClass().add("alert-warn");
                icon = new WarnIcon();
            }

            case null, default -> {
                this.getStyleClass().add("alert-info");
                icon = new InfoIcon();
            }
        }

        this.setPadding(new Insets(20));
        this.getChildren().setAll(icon, title, node, barContainer);
        getStylesheets().add(Objects.requireNonNull(Resources.class.getResource("css/alerts.css")).toExternalForm());

    }

    public ButtonBar getButtonBar() {
        return this.barAction;
    }
}
