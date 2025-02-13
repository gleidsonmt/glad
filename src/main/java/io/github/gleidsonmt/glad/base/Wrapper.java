package io.github.gleidsonmt.glad.base;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public interface Wrapper {

    void show();

    void show(WrapperEffect effect);

    void setOnClick(EventHandler<MouseEvent> eventHandler);

    void close();

    boolean isShowing();
}
