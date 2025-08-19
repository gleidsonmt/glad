package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.Anchor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

/**
 * Flow has the methods add children in the stack in the root.
 * This can be very useful to add animations, dialogs, alerts, etc.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Flow extends FlowItem<Flow> {
    // Utils
    /**
     * If they fit in the root (screen).
     * @param node The node to test.
     * @return true if fits.
     */
    @ApiStatus.Experimental
    boolean fits(Region node);

    @ApiStatus.Experimental
    void clearConstraints(Region node);

    // Setting

    /**
     * Define the content for this flow item.
     * @param content The content to be added.
     * @return The FlowItem.
     */
    Flow content(Region content);

    Flow width(double width);

    Flow height(double height);

    // Showing

    void show();

    void show(Region node);

    void show(MouseEvent node);

    // Removing

    /**
     * Remove a node from the root.
     * @param container The node to remove.
     */
    void remove(Node container);

    /**
     * Remove all nodes from the root.
     */
    void clear();

}
