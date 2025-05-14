package io.github.gleidsonmt.glad.notifications.component;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.notifications.factory.NotificationManager;
import javafx.geometry.HPos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/04/2025
 */
public class NotificationHeader extends GridPane {

    public NotificationHeader(NotificationManager manager) {
        getStyleClass().add("notification-footer");
        setVgap(10);
        Text title = new Text("Your notifications");
        title.getStyleClass().addAll("h4", "bold");
        Hyperlink link = new Hyperlink("Mark all as read");
        link.getStyleClass().addAll("h5", "bold");
        link.setGraphic(new SVGIcon(Icon.DONE_ALL));

        link.setOnAction(e -> {
            manager.clear();
        });

        add(title, 0, 0);
        add(link, 1, 0);

        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setHalignment(link, HPos.RIGHT);

        NotificationsToggle notificationsToggle = new NotificationsToggle(manager);
        GridPane.setHgrow(notificationsToggle, Priority.ALWAYS);
        GridPane.setColumnSpan(notificationsToggle, REMAINING);
        add(notificationsToggle, 0, 1);


    }
}
