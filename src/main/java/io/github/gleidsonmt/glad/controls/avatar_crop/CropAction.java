

package io.github.gleidsonmt.glad.controls.avatar_crop;

import javafx.scene.image.Image;

import java.util.EventListener;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  17/11/2024
 */
@FunctionalInterface
public interface CropAction extends EventListener {

    void crop(Image image);

}
