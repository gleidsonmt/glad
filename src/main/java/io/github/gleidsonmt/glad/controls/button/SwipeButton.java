package io.github.gleidsonmt.glad.controls.button;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.property.ObjectProperty;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
@SuppressWarnings("unused")
public class SwipeButton extends AnimatedButton {

    public SwipeButton() {
        super();
        getStyleClass().add("swipe-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SwipeButtonSkin(this);
    }

}