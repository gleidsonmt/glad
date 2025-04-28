package io.github.gleidsonmt.glad.controls.button;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class AlternateButton extends AnimatedButton {

    public AlternateButton() {
        super();
        getStyleClass().addAll("alternate-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new AlternateButtonSkin(this);
    }
}
