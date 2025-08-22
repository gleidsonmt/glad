package io.github.gleidsonmt.glad.controls.carousel;

import javafx.css.*;
import javafx.scene.control.Control;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/08/2025
 */
public class CarouselBase extends Control {

    private static final StyleablePropertyFactory<CarouselBase> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private static final CssMetaData<CarouselBase, Duration> TRANSITION_VELOCITY =
            FACTORY.createDurationCssMetaData("-fx-transition-velocity",
                    CarouselBase::transitionDelayProperty, Duration.millis(300));

    private static final CssMetaData<CarouselBase, Duration> TRANSITION_DELAY =
            FACTORY.createDurationCssMetaData("-fx-transition-delay",
                    CarouselBase::transitionDelayProperty, Duration.millis(5000));

    private static final CssMetaData<CarouselBase, Boolean> AUTO_RIDE =
            FACTORY.createBooleanCssMetaData("-fx-auto-ride",
                    CarouselBase::autoRideProperty, false);

    private final StyleableObjectProperty<Duration> transitionVelocity;
    private final StyleableObjectProperty<Duration> transitionDelay;
    private final StyleableBooleanProperty autoRide;

    public CarouselBase() {
        this.setPrefHeight(200D);
        this.setPrefWidth(200D);

        this.transitionVelocity = new SimpleStyleableObjectProperty<>(TRANSITION_VELOCITY, this, "transitionVelocity", Duration.millis(300D));
        this.transitionDelay = new SimpleStyleableObjectProperty<>(TRANSITION_DELAY, this, "transitionDelay", Duration.millis(5000));
        this.autoRide = new SimpleStyleableBooleanProperty(AUTO_RIDE, this, "autoRide", false);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public Duration getTransitionVelocity() {
        return transitionVelocity.get();
    }

    public StyleableObjectProperty<Duration> transitionVelocityProperty() {
        return transitionVelocity;
    }

    public Duration getTransitionDelay() {
        return transitionDelay.get();
    }

    public void setTransitionVelocity(Duration transitionVelocity) {
        this.transitionVelocity.set(transitionVelocity);
    }

    public StyleableObjectProperty<Duration> transitionDelayProperty() {
        return transitionDelay;
    }

    public void setTransitionDelay(Duration transitionDelay) {
        this.transitionDelay.set(transitionDelay);
    }

    public boolean isAutoRide() {
        return autoRide.get();
    }

    public StyleableBooleanProperty autoRideProperty() {
        return autoRide;
    }

    public void setAutoRide(boolean autoRide) {
        this.autoRide.set(autoRide);
    }
}
