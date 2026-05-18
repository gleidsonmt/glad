

package io.github.gleidsonmt.glad.drawer;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
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
