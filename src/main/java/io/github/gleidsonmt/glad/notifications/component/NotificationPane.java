package io.github.gleidsonmt.glad.notifications.component;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/04/2025
 */
public class NotificationPane extends VBox {

    public NotificationPane(Node... children) {
        super(children);
        getStyleClass().addAll("rounded", "padding-20", "notification-pane");
        setSpacing(10);
    }
}
