package io.github.gleidsonmt.glad.base;

import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
public interface LayoutActions {

    default Node getRoot() {
        return (Node) this;
    }

    void updateView(Node view);
}
