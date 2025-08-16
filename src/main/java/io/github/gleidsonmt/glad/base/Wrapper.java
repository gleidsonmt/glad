package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.App;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

/**
 * The wrapper interface disposes a region with color to focus on the element stacked.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Wrapper extends FlowItem<Wrapper> {

    void show(WrapperEffect effect);

    Wrapper onClick(EventHandler<MouseEvent> eventHandler);

    Wrapper with(Region node);

    boolean isShowing();
}
