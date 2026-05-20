

package io.github.gleidsonmt.glad.base.dialog;

import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.FlowItem;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * The wrapper interface disposes a region with color to focus on the element stacked.
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  26/01/2025
 */
@Deprecated(forRemoval = true)
public interface Wrapper extends FlowItem<Wrapper> {

    void show(WrapperEffect effect);

    Wrapper onClick(EventHandler<MouseEvent> eventHandler);

    Wrapper with(Region node);

    Wrapper with(Flow node);

    boolean isShowing();
}
