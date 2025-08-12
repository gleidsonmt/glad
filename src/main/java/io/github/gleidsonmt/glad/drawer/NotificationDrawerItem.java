package io.github.gleidsonmt.glad.drawer;

import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/04/2025
 */
public class NotificationDrawerItem extends DrawerItemBase {

    private final Node icon;

    public NotificationDrawerItem(String name, Node graphic, Node icon, Node content) {
        super(name, graphic, content);
        this.icon = icon;
    }

    public Node getIcon() {
        return icon;
    }
}
