package io.github.gleidsonmt.glad.notifications.factory;

import io.github.gleidsonmt.glad.notifications.component.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Separator;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/04/2025
 */
@SuppressWarnings("unused")
public class NotificationManager {

    private final NotificationPane pane;
    private final ObservableList<NotificationItem<Notification>> notifications;
    private final FilteredList<NotificationItem<Notification>> filteredNotifications;

    public NotificationManager() {
        notifications = FXCollections.observableArrayList();
        filteredNotifications = new FilteredList<>(notifications, (_) -> true);

        pane = new NotificationPane(
                new NotificationHeader(this),
                new Separator(),
                new NotificationBody(this),
                new Separator(),
                new NotificationFooter());
    }


    public void add(NotificationItem<Notification> notification) {
        notifications.add(notification);
    }

    @SafeVarargs
    public final void addAll(NotificationItem<Notification>... notification) {
        notifications.addAll(notification);
    }

    @SafeVarargs
    public final void removeAll(NotificationItem<Notification>... notification) {
        notifications.removeAll(notification);
    }

    public void remove(NotificationItem<Notification>  notification) {
        notifications.remove(notification);
    }

    public void remove(int from, int to) {
        notifications.remove(from, to);
    }

    public void remove(int index) {
        notifications.remove(index);
    }

    public void clear() {
        notifications.clear();
    }

    public Region getRoot() {
        return pane;
    }

    public ObservableList<NotificationItem<Notification> > getNotifications() {
        return notifications;
    }

    public FilteredList<NotificationItem<Notification> > getFilteredNotifications() {
        return filteredNotifications;
    }


}
