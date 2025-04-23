package io.github.gleidsonmt.glad.controls.avatar;

import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Platform;
import javafx.css.converter.ColorConverter;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/04/2025
 */
public final class CircleAvatarStatus extends Circle {

    CircleAvatarStatus(AvatarStatus avatarStatus) {
        setRadius(5);
        setStroke(Color.WHITE);
        setFill(Color.RED);
        setCursor(Cursor.HAND);

        ContextMenu contextMenu = new ContextMenu();
        ThemeProvider.install(contextMenu.getScene(), Css.DEFAULT);

        avatarStatus. setOnMouseClicked(e -> {
            contextMenu.show(this, Side.BOTTOM, 0,0);
        });

        for (Status status : Status.values()) {
            MenuItem menuItem = new MenuItem(status.toString());
            Circle graphic = new Circle();
            graphic.setRadius(5);
            menuItem.setGraphic(graphic);
            graphic.setStyle("-fx-fill: -light-gray-2;");

            _switch(graphic, status);

            menuItem.setOnAction(e -> {
                avatarStatus.setStatus(status);
                _switch(this, status);
            });
            contextMenu.getItems().add(menuItem);
        }
    }
    private void _switch(Circle circle, Status status) {
        if (status == Status.BUSY) {
            circle.setStyle("-fx-fill: -danger;");
        }
        if (status == Status.OFFLINE) {
            circle.setStyle("-fx-fill: -light-gray-2;");
        }

        if (status == Status.ONLINE) {
            circle.setStyle("-fx-fill: -success;");
        }

        if (status == Status.AWAY) {
            circle.setStyle("-fx-fill: -warning;");
        }
    }
}
