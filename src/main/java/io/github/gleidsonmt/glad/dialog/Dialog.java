package io.github.gleidsonmt.glad.dialog;

import io.github.gleidsonmt.glad.base.FlowItem;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import io.github.gleidsonmt.glad.base.internal.DialogBase;
import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/03/2025
 */
public interface Dialog extends DialogBase<Dialog> {

    @ApiStatus.Experimental
    Dialog effect();



}
