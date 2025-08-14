package io.github.gleidsonmt.glad.base;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.ApiStatus;

/**
 * The wrapper interface disposes a region with color to focus on the element stacked.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Wrapper extends FlowItem<Wrapper>{

    void show();

    void show(WrapperEffect effect);

    @Deprecated(forRemoval = true)
    void setOnClick(EventHandler<MouseEvent> eventHandler);

    Wrapper onClick(EventHandler<MouseEvent> eventHandler);

    void hide();

    @ApiStatus.Experimental
    void back();

    boolean isShowing();
}
