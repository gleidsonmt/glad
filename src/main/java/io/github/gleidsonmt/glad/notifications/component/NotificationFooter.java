package io.github.gleidsonmt.glad.notifications.component;

import io.github.gleidsonmt.glad.controls.button.Button;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/04/2025
 */
public class NotificationFooter extends GridPane {

    public NotificationFooter() {
        getStyleClass().add("notification-footer");
        setMinHeight(60);
        Button viewAll = new Button("View all notifications");
        Button manage = new Button("Manage notifications");
        manage.getStyleClass().addAll("flat");

        add(manage, 0, 0);
        add(viewAll, 1, 0);

        GridPane.setHalignment(viewAll, HPos.RIGHT);

        ColumnConstraints one = new ColumnConstraints();
        one.setPercentWidth(50);
        ColumnConstraints two = new ColumnConstraints();
        two.setPercentWidth(50);
        getColumnConstraints().addAll(one, two);
    }
}
