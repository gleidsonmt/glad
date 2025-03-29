package io.github.gleidsonmt.glad.controls;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.*;
import javafx.css.converter.PaintConverter;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2025
 */
public class ToggleSwitch extends Control {

    private final BooleanProperty on = new SimpleBooleanProperty(false);

    // StyleableProperty
    private final StyleableProperty<Color> color =
            new SimpleStyleableObjectProperty<>(COLOR, this, "color");

    public ToggleSwitch() {
        getStyleClass().add("toggle-switch");
    }

    // StyleablePropertyFactory
    private static final StyleablePropertyFactory<ToggleSwitch> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    // CssMetaData from StyleablePropertyFactory
    private static final CssMetaData<ToggleSwitch, Color> COLOR =
            FACTORY.createColorCssMetaData("-fx-color-animation", s -> s.color, Color.RED, false);

    // Return all CssMetadata information from StyleablePropertyFactory
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }
    // Typical JavaFX property implementation
    public Color getColor() {
        return this.color.getValue();
    }
    public void setColor(final Color color) {
        this.color.setValue(color);
    }
    public ObjectProperty<Color> colorProperty() {
        return (ObjectProperty<Color>) this.color;
    }

    public boolean isOn() {
        return on.get();
    }

    public BooleanProperty onProperty() {
        return on;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ToggleSwitchSkin(this);
    }
}
