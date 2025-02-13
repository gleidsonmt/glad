package io.github.gleidsonmt.glad.base;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class Container extends BorderPane {

    public Container() {
        this(null);
    }

    public Container(Node body) {
        this.setCenter(body);
    }

    public void updateView(Node node) {
        this.setCenter(node);
    }
}
