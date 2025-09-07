package io.github.gleidsonmt.glad.base.dialog;

import io.github.gleidsonmt.glad.base.internal.DialogBase;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/03/2025
 */
public interface Dialog extends DialogBase<Dialog> {

    @ApiStatus.Experimental
    Dialog effect();


}
