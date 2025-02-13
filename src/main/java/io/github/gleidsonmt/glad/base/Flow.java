package io.github.gleidsonmt.glad.base;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Flow {

    void openAbsolute(Node container, Pos position, Insets insets);

    void openLeft(Node container);

    void openLeft(Node container, Insets insets);

    void openRight(Node container);

    void openRight(Node container, Insets insets);

    void remove(Node container);

}
