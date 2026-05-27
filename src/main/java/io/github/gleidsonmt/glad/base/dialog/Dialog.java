

package io.github.gleidsonmt.glad.base.dialog;

import io.github.gleidsonmt.glad.base.internal.DialogBase;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  22/03/2025
 */
public interface Dialog extends DialogBase<Dialog> {

    @Deprecated
    @ApiStatus.Experimental
    Dialog effect();

}
