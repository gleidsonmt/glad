package io.github.gleidsonmt.glad.drawer;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2025
 */
public interface DrawerCell {
    String getName();

    Node getGraphic();

//    default Node getIcon() {
//        return null;
//    }
//
//    ObjectProperty<Node> contentProperty();
}
