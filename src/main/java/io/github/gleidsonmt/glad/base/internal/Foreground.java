/*
 * *
 *  * Description:
 *  *
 *  * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 *  * Create on ${DATE}
 *
 */

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 31/05/2026
 */
public interface Foreground {

    void show();

    void hide();

    void addAction(EventHandler<MouseEvent> eventHandler);

    void removeAction();

    void restyle(WrapperEffect effect);

}
