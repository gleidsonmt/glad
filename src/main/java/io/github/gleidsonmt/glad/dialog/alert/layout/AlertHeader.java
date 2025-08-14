package io.github.gleidsonmt.glad.dialog.alert.layout;

import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.scene.layout.Pane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/11/2024
 */
public class AlertHeader extends Pane {

    public AlertHeader(AlertType type) {
        this.getStyleClass().addAll("alert-header");
        this.setMinHeight(35D);
        this.setPrefHeight(35D);
        this.setMaxHeight(35D);

        switch (type) {
            case ERROR -> this.getStyleClass().add("alert-error");
            case SUCCESS -> this.getStyleClass().add("alert-success");
            case WARNING -> this.getStyleClass().add("alert-warning");
            case null, default -> this.getStyleClass().add("alert-info");

        }
    }
}
