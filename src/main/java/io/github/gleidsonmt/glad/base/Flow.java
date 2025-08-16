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

    @Deprecated(forRemoval = true)
    void openLeft(Node container);

    @Deprecated(forRemoval = true)
    void openLeft(Node container, Insets insets);

    @Deprecated(forRemoval = true)
    void openRight(Node container);

    @Deprecated(forRemoval = true)
    void openRight(Node container, Insets insets);

    // Testing
    @ApiStatus.Experimental
    void openByCursor(Region container, MouseEvent e);

    @ApiStatus.Experimental
    void openByCursor(Region container, MouseEvent e, Pos pos);

    @ApiStatus.Experimental
    void openByCursor(Region container, MouseEvent e, Pos pos, double x, double y);

    @ApiStatus.Experimental
    void openByNode(Region container, Node node, Pos pos);

    @ApiStatus.Experimental
    void openByNode(Region container, Node node, Pos pos, double x, double y);

    @Deprecated
    @ApiStatus.Experimental
    void openByCursor(Region container, MouseEvent e, double x, double y);

    /**
     * If they fit in the root (screen).
     * @param node The node to test.
     * @return true if fits.
     */
    @ApiStatus.Experimental
    boolean fits(Region node);


    @ApiStatus.Experimental
    void clearConstraints(Region node);

    // Fixed

    /**
     * Open directly in root.
     * Default insets 0
     * @param container The node to add.
     * @param position The default pos.
     */
    @Deprecated(forRemoval = true)
    void openAbsolute(Node container, Pos position);

    /**
     * Open directly in root.
     * @param container The node to add.
     * @param position The default pos.
     * @param insets The default insets.
     */
    @Deprecated(forRemoval = true)
    void openAbsolute(Node container, Pos position, Insets insets);


    void show();

    void show(Region node);

    void show(MouseEvent node);

    /**
     * Remove a node from the root.
     * @param container The node to remove.
     */
    void remove(Node container);

    /**
     * Remove all nodes from the root.
     */
    void clear();



    /**
     * Define a content for this flow item.
     * @param content The content to be added.
     * @return The FlowItem.
     */
    Flow content(Region content);

    Flow width(double width);

    Flow width(double width, double min);

    Flow width(double width, double min, double max);

    Flow height(double height);

    Flow height(double height, double min);

    Flow height(double height, double min, double max);


}
