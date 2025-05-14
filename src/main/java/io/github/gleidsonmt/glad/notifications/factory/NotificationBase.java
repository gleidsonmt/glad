package io.github.gleidsonmt.glad.notifications.factory;


import io.github.gleidsonmt.glad.User;

import java.time.LocalDateTime;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/04/2025
 */
@SuppressWarnings("unused")
public class NotificationBase implements Notification {

    private User user;
    private LocalDateTime dateTime;
    private boolean visualized;

    public NotificationBase(User user, LocalDateTime dateTime, boolean visualized) {
        this.user = user;
        this.dateTime = dateTime;
        this.visualized = visualized;
    }

    @Override
    public NotificationType getType() {
        return null;
    }

    @Override
    public boolean visualized() {
        return visualized;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isVisualized() {
        return visualized;
    }

    public void setVisualized(boolean visualized) {
        this.visualized = visualized;
    }
}
