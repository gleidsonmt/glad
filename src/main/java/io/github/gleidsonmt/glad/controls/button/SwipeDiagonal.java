package io.github.gleidsonmt.glad.controls.button;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class SwipeDiagonal extends AnimatedButton {
    public SwipeDiagonal() {
        super();
        getStyleClass().add("swipe-diagonal");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SwipeDiagonalSkin(this);
    }
}
