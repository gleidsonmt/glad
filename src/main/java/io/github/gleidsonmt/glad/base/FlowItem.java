package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.Anchor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2025
 */
public interface FlowItem<T> extends AppearItem {

    T insets(Insets insets);

    /**
     * Anchor your region.
     * @param anchor The anchor.
     * @return The FlowItem.
     */
    T anchor(Anchor anchor);

    /**
     * The position of the content.
     * @param pos The position.
     * @return The FlowItem.
     */
    T pos(Pos pos);
}
