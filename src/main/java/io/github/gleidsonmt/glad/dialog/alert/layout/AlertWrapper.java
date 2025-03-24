package io.github.gleidsonmt.glad.dialog.alert.layout;

import io.github.gleidsonmt.glad.dialog.DialogContainer;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/11/2024
 */
public class AlertWrapper extends VBox {

    private final AlertContent content;

    public AlertWrapper(AlertType type) {
        this.getStyleClass().add("alert-container");
        AlertHeader header = new AlertHeader(type);
        this.content = new AlertContent(type);

        DialogContainer container = new DialogContainer();

        container.getChildren().add(content);
        this.getChildren().addAll(header, container);
        VBox.setVgrow(this.content, Priority.ALWAYS);

    }

    public AlertContent getContent(){
        return this.content;
    }


}
