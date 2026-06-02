/*
 * *
 *  * Description:
 *  *
 *  * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 *  * Create on ${DATE}
 *
 */

package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.dialog.Alert;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.internal.ContentItem;
import io.github.gleidsonmt.glad.base.internal.DialogBase;
import javafx.scene.Node;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 02/06/2026
 */
public interface Drawer {

    Drawer with(WrapperEffect wrapperEffect);

    Drawer content(Node content);

    void show();

    void hide();
}
