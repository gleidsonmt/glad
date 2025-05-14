package io.github.gleidsonmt.glad.notifications;


import io.github.gleidsonmt.glad.User;
import io.github.gleidsonmt.glad.notifications.factory.NotificationBase;
import io.github.gleidsonmt.glad.notifications.factory.NotificationType;

import java.time.LocalDateTime;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/04/2025
 */
public class CommentNotification extends NotificationBase {

    private final String post;

    public CommentNotification(User user, LocalDateTime dateTime, boolean visualized, String post) {
        super(user, dateTime, visualized);
        this.post = post;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.COMMENT;
    }

    public String getPost() {
        return post;
    }
}
