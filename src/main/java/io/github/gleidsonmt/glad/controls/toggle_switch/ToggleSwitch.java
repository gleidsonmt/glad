package io.github.gleidsonmt.glad.controls.toggle_switch;

import io.github.gleidsonmt.glad.GladResources;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;

/**
 * A toggle specialized in turning off and on action.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2025
 */
public class ToggleSwitch extends Control {

    private final BooleanProperty on = new SimpleBooleanProperty(false);

    // StyleableProperty
    private final StyleableProperty<Color> animationColor =
            new SimpleStyleableObjectProperty<>(ANIMATION_COLOR, this, "animationColor", Color.RED);

    private final StyleableProperty<Color> trackColor =
            new SimpleStyleableObjectProperty<>(TRACK_COLOR, this, "trackColor", Color.WHITE);

    private final StyleableProperty<Number> arc =
            new SimpleStyleableObjectProperty<>(ARC_SIZE, this, "arc", 25D);

    private final StyleableProperty<Number> trackSize =
            new SimpleStyleableObjectProperty<>(TRACK_SIZE, this, "trackSize", 25D);

    public ToggleSwitch() {
        this(false);
    }

    public ToggleSwitch(boolean on) {
        getStyleClass().add("toggle-switch");
        setOn(on);
    }

    // StyleablePropertyFactory
    private static final StyleablePropertyFactory<ToggleSwitch> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    // CssMetaData from StyleablePropertyFactory
    private static final CssMetaData<ToggleSwitch, Color> ANIMATION_COLOR =
            FACTORY.createColorCssMetaData("-fx-color-animation", s -> s.animationColor, Color.RED, false);

    private static final CssMetaData<ToggleSwitch, Color> TRACK_COLOR =
            FACTORY.createColorCssMetaData("-fx-track-color", s -> s.trackColor, Color.RED, false);

    private static final CssMetaData<ToggleSwitch, Number> ARC_SIZE =
            FACTORY.createSizeCssMetaData("-fx-arc-size", s -> s.arc, 25D, false);

    private static final CssMetaData<ToggleSwitch, Number> TRACK_SIZE =
            FACTORY.createSizeCssMetaData("-fx-track-size", s -> s.trackSize, 25D, false);


    // Return all CssMetadata information from StyleablePropertyFactory
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }
    // Typical JavaFX property implementation
    public Color getTrackColor() {
        return this.trackColor.getValue();
    }

    public StyleableProperty<Number> getTrackSize() {
        return trackSize;
    }

    public Color getAnimationColor() {
        return this.animationColor.getValue();
    }

    public void setColor(final Color color) {
        this.animationColor.setValue(color);
    }

    public void setOn(boolean on) {
        this.on.set(on);
    }

    public boolean isOn() {
        return on.get();
    }

    public BooleanProperty onProperty() {
        return on;
    }

    public StyleableProperty<Number> getArc() {
        return arc;
    }
    public ObjectProperty<Color> animationColorProperty() {
        return (ObjectProperty<Color>) this.animationColor;
    }

    public ObjectProperty<Color> trackColorProperty() {
        return (ObjectProperty<Color>) this.trackColor;
    }

    public ObjectProperty<Number> arcProperty() {
        return (ObjectProperty<Number>) this.arc;
    }

    public ObjectProperty<Number> trackSizeProperty() {
        return (ObjectProperty<Number>) this.trackSize;
    }

    public void setArc(final Number number) {
        this.arc.setValue(number);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ToggleSwitchSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(GladResources.class.getResource("css/toggle-switch.css")).toExternalForm();
    }
}
