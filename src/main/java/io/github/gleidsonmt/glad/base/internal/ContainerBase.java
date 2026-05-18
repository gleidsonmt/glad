

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.FlowItem;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  05/09/2025
 */
public interface ContainerBase<T> extends FlowItem<T> {

    T content(Region node);

    T width(double width);

    T height(double height);

}
