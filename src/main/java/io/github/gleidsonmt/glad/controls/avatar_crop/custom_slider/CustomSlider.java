package io.github.gleidsonmt.glad.controls.avatar_crop.custom_slider;

import io.github.gleidsonmt.glad.Resources;
import javafx.scene.control.Skin;
import javafx.scene.control.Slider;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/11/2024
 */
public class CustomSlider extends Slider {

    public CustomSlider() {

    }

    public CustomSlider(double min, double max, double value) {
        setMax(max);
        setMin(min);
        setValue(value);

        getStylesheets().add(Objects.requireNonNull(Resources.getCss("custom-slider.css")));
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomSliderSkin(this);
    }
}
