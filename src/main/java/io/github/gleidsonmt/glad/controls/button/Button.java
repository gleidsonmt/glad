

package io.github.gleidsonmt.glad.controls.button;

import javafx.beans.DefaultProperty;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  29/09/2022
 */
@DefaultProperty("control")
public class Button extends javafx.scene.control.Button {

    private static final StyleablePropertyFactory<Button> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableProperty<Color> circleFill =
            new SimpleStyleableObjectProperty<>(CIRCLE_FILL, this, "circleFill", Color.WHITE);

    private static final CssMetaData<Button, Color> CIRCLE_FILL =
            FACTORY.createColorCssMetaData("-fx-ripple-fill",
                    Button::circleFillProperty, Color.WHITE, true);

    public Button() {
        this(null);
    }

    public Button(String text) {
        setText(text == null ? "Button" : text);
        setAlignment(Pos.CENTER);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RippleButtonSkin(this);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public StyleableProperty<Color> circleFillProperty() {
        return circleFill;
    }

    public Color getCircleFill() {
        return circleFill.getValue();
    }


}