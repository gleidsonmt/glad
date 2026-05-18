

package io.github.gleidsonmt.glad.controls.avatar_crop.footer;

import io.github.gleidsonmt.glad.controls.avatar_crop.image_container.ImageContainer;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  17/11/2024
 */
public class Buttons extends ButtonBar {

    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    public Buttons(ImageContainer imageContainer) {
        saveButton.setDefaultButton(true);
        cancelButton.setCancelButton(true);
        setButtonData(saveButton, ButtonData.OTHER);
        setButtonMinWidth(150);
        saveButton.setMinHeight(40);
        cancelButton.setMinHeight(40);
        this.getButtons().setAll(cancelButton, saveButton);

    }


    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
