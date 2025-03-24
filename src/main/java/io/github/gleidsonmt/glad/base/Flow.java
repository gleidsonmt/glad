package io.github.gleidsonmt.glad.base;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * Flow has the methods add children in stack in the root.
 * This can be very useful, to add animations, dialogs, alerts, etc.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Flow {

    /**
     * Open directly in root.
     * @param container The node to add.
     * @param position The default pos.
     * @param insets The default insets.
     */
    void openAbsolute(Node container, Pos position, Insets insets);

    /**
     * Open directly in root.
     * Default insets 0
     * @param container The node to add.
     * @param position The default pos.
     */
    void openAbsolute(Node container, Pos position);

    /**
     * Open directly in root.
     * Default pos center, and insets 0
     * @param container The node to add.
     */
    void openAbsolute(Node container);

    void openLeft(Node container);

    void openLeft(Node container, Insets insets);

    void openRight(Node container);

    void openRight(Node container, Insets insets);

    void remove(Node container);

    void clear();

}
