

package io.github.gleidsonmt.glad.controls.avatar_crop;

import java.util.EventListener;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  20/11/2024
 */
@FunctionalInterface
public interface CancelAction extends EventListener {

    void cancel();

}
