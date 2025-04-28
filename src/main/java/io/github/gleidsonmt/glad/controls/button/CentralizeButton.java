package io.github.gleidsonmt.glad.controls.button;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class CentralizeButton extends AnimatedButton {

    public CentralizeButton() {
        super();
        getStyleClass().addAll("centralize-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CentralizeButtonSkin(this);
    }
}
