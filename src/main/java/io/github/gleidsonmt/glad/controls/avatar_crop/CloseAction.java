package io.github.gleidsonmt.glad.controls.avatar_crop;

import java.util.EventListener;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/11/2024
 */
@FunctionalInterface
public interface CloseAction  extends EventListener {

    void close();

}
