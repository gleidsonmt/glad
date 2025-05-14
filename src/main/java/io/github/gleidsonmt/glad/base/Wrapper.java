package io.github.gleidsonmt.glad.base;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The wrapper interface disposes a region with color to focus on the element stacked.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Wrapper {

    void show();

    void show(WrapperEffect effect);

    void setOnClick(EventHandler<MouseEvent> eventHandler);

    void hide();

    void back();

    boolean isShowing();
}
