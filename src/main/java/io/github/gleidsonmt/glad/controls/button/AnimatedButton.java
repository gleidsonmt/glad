package io.github.gleidsonmt.glad.controls.button;

import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class AnimatedButton extends Button {

    private static final StyleablePropertyFactory<AnimatedButton> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private static final CssMetaData<AnimatedButton, Color> TRANSITION_COLOR =
            FACTORY.createColorCssMetaData("-fx-trans-fill",
                    AnimatedButton::transitionColorProperty, Color.WHITE, true);

    private static final CssMetaData<AnimatedButton, Color> TRANSITION_TEXT_COLOR =
            FACTORY.createColorCssMetaData("-fx-trans-text-fill",
                    AnimatedButton::transitionTextColorProperty, Color.WHITE, true);

    private final StyleableProperty<Color> transitionColor =
            new SimpleStyleableObjectProperty<>(TRANSITION_COLOR, this, "transitionColor", Color.RED);

    private final StyleableProperty<Color> transitionTextFill =
            new SimpleStyleableObjectProperty<>(TRANSITION_TEXT_COLOR, this, "transitionTextColor", Color.WHITE);

    public AnimatedButton() {
        super();
        getStyleClass().addAll("animated-button");
    }


    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public Color getTransitionColor() {
        return transitionColor.getValue();
    }

    public Color getTransitionTextFill() {
        return transitionTextFill.getValue();
    }

    public void setTransitionColor(Color transitionColor) {
        this.transitionColor.setValue(transitionColor);
    }

    public void setTransitionTextFill(Color transitionTextFill) {
        this.transitionTextFill.setValue(transitionTextFill);
    }

    public Color getTransitionFill() {
        return this.transitionColor.getValue();
    }

    public StyleableProperty<Color> transitionColorProperty() {
        return transitionColor;
    }

    public StyleableProperty<Color> transitionTextColorProperty() {
        return transitionTextFill;
    }
}
