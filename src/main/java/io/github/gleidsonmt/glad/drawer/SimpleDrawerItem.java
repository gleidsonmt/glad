package io.github.gleidsonmt.glad.drawer;

import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/04/2025
 */
public class SimpleDrawerItem extends DrawerItemBase implements DrawerItem {

    public SimpleDrawerItem(String name, Node graphic, Node content) {
        super(name, graphic, content);
    }
}
