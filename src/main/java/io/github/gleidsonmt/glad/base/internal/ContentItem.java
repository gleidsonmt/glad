/*
 * *
 *  * Description:
 *  *
 *  * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 *  * Create on ${DATE}
 *
 */

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.FlowItem;
import javafx.scene.Node;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 02/06/2026
 */
public interface ContentItem extends FlowItem {

    ContentItem content(Node content);

}
