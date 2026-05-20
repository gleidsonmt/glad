

package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.internal.AppearItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  12/08/2025
 */
public interface FlowItem<T> extends AppearItem {

    T insets(Insets insets);

    /**
     * Anchor your region.
     *
     * @param anchor The anchor.
     * @return The FlowItem.
     */
    T anchor(Anchor anchor);

    /**
     * The position of the content.
     *
     * @param pos The position.
     * @return The FlowItem.
     */
    T pos(Pos pos);

    /**
     * Blocks the UI with a pane.
     * Only makes sense with a combination with {@link #with(WrapperEffect)}
     * @return The FlowItem.
     */
    @ApiStatus.Experimental
    T block();

    /**
     *  Change the effect of the foreground of the flow item.
     * @param wrapperEffect The effect of the foreground of the flow item.
     * @return The FlowItem.
     */
    T with(WrapperEffect wrapperEffect);

}
