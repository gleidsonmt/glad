package io.github.gleidsonmt.glad.dialog.alert.layout;

import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/11/2024
 */
public class AlertContent extends VBox {

    private final Text title;
    private final BarAction barAction;

    public AlertContent(AlertType type) {
        this.title = new Text();
        this.title.getStyleClass().addAll("alert-title", "h4", "bold");
        this.barAction = new BarAction();
//        this.getStyleClass().add("alert-content");
        this.getChildren().addAll(this.title, this.barAction);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setPadding(new Insets(30));
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        switch (type) {
            case ERROR -> {
                this.title.getStyleClass().add("title-error");
                this.barAction.getStyleClass().add("bar-error");
                this.title.getStyleClass().add("text-danger");
            }
            case SUCCESS -> {
                this.title.getStyleClass().add("title-success");
                this.barAction.getStyleClass().add("bar-success");
                this.title.getStyleClass().add("text-success");
            }
            case WARNING -> {
                this.barAction.getStyleClass().add("bar-warning");
                this.title.getStyleClass().add("text-warning");
            }
            case null, default -> {
                this.barAction.getStyleClass().add("bar-info");
                this.title.getStyleClass().add("text-info");
            }

        }
    }

    public ButtonBar getButtonBar() {
        return this.barAction;
    }

    public Text getTitle() {
        return title;
    }

    public void setNode(Node node) {
        this.getChildren().add(1, node);
    }

}
