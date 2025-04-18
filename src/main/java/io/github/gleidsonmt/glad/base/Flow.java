package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.animations.Anchor;
import io.github.gleidsonmt.glad.base.internal.animations.Animation;
import io.github.gleidsonmt.glad.base.internal.animations.Animations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

/**
 * Flow has the methods add children in stack in the root.
 * This can be very useful, to add animations, dialogs, alerts, etc.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Flow {

    /**
     * Open directly in root.
     * Default insets 0
     * @param container The node to add.
     * @param position The default pos.
     */
    @Deprecated
    void openAbsolute(Node container, Pos position);

    /**
     * Open directly in root.
     * @param container The node to add.
     * @param position The default pos.
     * @param insets The default insets.
     */
    @Deprecated
    void openAbsolute(Node container, Pos position, Insets insets);

    /**
     * Open directly in root.
     * Default pos center, and insets 0
     * @param container The node to add.
     */
    @Deprecated
    void openAbsolute(Node container);

    @Deprecated
    void openAbsolute(Pos pos, Node container, Insets insets, Animations animation);

    @Deprecated
    void openAbsolute(Pos pos, Node container, Animations animation);

    @Deprecated
    void openLeft(Node container);

    @Deprecated
    void openLeft(Node container, Insets insets);

    @Deprecated
    void openRight(Node container);

    @Deprecated
    void openRight(Node container, Insets insets);

    // Testing
    @ApiStatus.Experimental
    void openByCursor(Region container, MouseEvent e);

    // Fixed
    Flow anchor(Anchor anchor);

    Flow insets(Insets insets);

    Flow pos(Pos pos);

    Flow content(Node node);

    Flow with(WrapperEffect effect);

    void show();

    void remove(Node container);

    void clear();
}
