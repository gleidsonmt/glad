

package io.github.gleidsonmt.glad.controls.avatar_crop.image_container;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  14/11/2024
 */
public class AvatarCropImageView extends ImageView {

    public AvatarCropImageView(Image image) {
        setImage(image);
//        this.setEffect(new ColorAdjust(0, 0, -0.55, 0));
        this.setPreserveRatio(true);
        this.setFitWidth(500);
//        cropImageView.setFitWidth(_image.getWidth() * (newValue.doubleValue()  -0.7));
//        this.setFitHeight(200);
//        this.setViewport(new Rectangle2D(0,0, 500, 500));
    }
}
