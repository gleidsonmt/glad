package io.github.gleidsonmt.glad.controls.avatar_crop.footer;

import io.github.gleidsonmt.glad.controls.avatar_crop.image_container.AvatarCropImageView;
import io.github.gleidsonmt.glad.controls.avatar_crop.image_container.ImageContainer;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/11/2024
 */
public class Footer extends VBox {

    private final Buttons buttons;

    public Footer( ImageContainer imageContainer) {
        this.setMaxWidth(500);
        AvatarCropImageView cropImageView = imageContainer.getScrollView().getScrollViewContent();
        Controls controls = new Controls(cropImageView);
        buttons = new Buttons(imageContainer);
        this.setSpacing(10);
        this.getChildren().setAll(controls, buttons);
    }

    public Buttons getButtonBar() {
        return buttons;
    }

}
