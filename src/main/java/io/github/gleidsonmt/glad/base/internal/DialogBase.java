package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.FlowItem;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/09/2025
 */
public interface DialogBase<T> extends FlowItem<T> {

    T width(double width);

    T height(double height);

    T title(String title);

    T content(Region node);

    T effect(WrapperEffect effect);

    T full();
}
