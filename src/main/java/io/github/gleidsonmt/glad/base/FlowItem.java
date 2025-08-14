package io.github.gleidsonmt.glad.base;

import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2025
 */
public interface FlowItem<T> {

    /**
     * Method called to put the content on the screen.
     */
    void show();

    /**
     * Method called to remove the content on the screen.
     */
    void hide();

    /**
     * Defin a content for this flow item.
     * @param content The content to be added.
     * @return The FlowItem.
     */
    T content(Node content);

    /**
     * The position of the content.
     * @param pos The position.
     * @return The FlowItem.
     */
    T pos(Pos pos);
}
