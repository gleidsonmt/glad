package io.github.gleidsonmt.glad.drawer;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/04/2025
 */
public interface DrawerItem {
        String getName();

        Node getGraphic();

        default Node getIcon() {
                return null;
        }

        ObjectProperty<Node> contentProperty();
}
