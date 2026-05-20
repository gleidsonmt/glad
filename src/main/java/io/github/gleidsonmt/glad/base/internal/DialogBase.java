

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.FlowItem;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  05/09/2025
 */
public interface DialogBase<T> extends FlowItem<T> {

    T width(double width);

    T height(double height);

    T title(String title);

    T content(Region node);

    @Deprecated(forRemoval = true)
    T effect(WrapperEffect effect);

    @ApiStatus.Experimental
    T full();

}
