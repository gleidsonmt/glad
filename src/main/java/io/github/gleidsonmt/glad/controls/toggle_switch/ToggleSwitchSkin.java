package io.github.gleidsonmt.glad.controls.toggle_switch;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2025
 */
public class ToggleSwitchSkin extends SkinBase<ToggleSwitch> {

    private Pane pane = new Pane();

    private BooleanProperty on = new SimpleBooleanProperty(false);

    private final TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.15));
    private final FillTransition fillAnimation = new FillTransition(Duration.seconds(0.15));
    private final StrokeTransition strokeTransition = new StrokeTransition(Duration.seconds(0.15));
    private final ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation, strokeTransition);

    private final Rectangle trigger = new Rectangle();
    private final Rectangle background = new Rectangle(50, 25);

    protected ToggleSwitchSkin(ToggleSwitch control) {
        super(control);
        getChildren().add(pane);
        trigger.getStyleClass().add("trigger");
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        trigger.setHeight(20);
        trigger.setWidth(20);
        trigger.setArcWidth(25);
        trigger.setArcHeight(25);

        background.setCursor(Cursor.HAND);
        background.getStyleClass().add("foreground");
        background.setArcWidth(25);
        background.setArcHeight(25);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);
        getChildren().addAll(background, trigger);

        background.setFill(control.getTrackColor());

        Color strokeInit = (Color) background.getStroke();

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);
        strokeTransition.setShape(background);

        on.bindBidirectional(control.onProperty());
        if (control.isOn()) {
            background.setFill(control.getAnimationColor());
            trigger.setTranslateX(25);
        }

        control.setOnMouseClicked(e -> {
            translateAnimation.setToX(
                    control.isOn() ? 0 : 25
            );

            fillAnimation.setFromValue(on.get() ? control.getTrackColor() : control.getAnimationColor());
            fillAnimation.setToValue(!on.get() ? control.getAnimationColor() : control.getTrackColor());

            strokeTransition.setFromValue(on.get() ? control.getAnimationColor() : strokeInit);
            strokeTransition.setToValue(!on.get() ? control.getAnimationColor() : strokeInit);
            animation.setOnFinished(_ -> on.set(!on.get()));
            animation.play();
        });
        
        registerChangeListener(control.arcProperty(), c -> {
            if (c.getValue() != null) {
                double val = (double) c.getValue();
                if (val >= 25) {
                    val = 25;
                }
                trigger.setArcHeight(val);
                trigger.setArcWidth(val);
                background.setArcHeight(val);
                background.setArcWidth(val);
            }
        });

        registerChangeListener(control.trackSizeProperty(), c -> {
            if (c.getValue() != null) {
                background.setHeight((double) c.getValue());
            }
        });

    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        layoutInArea(trigger, contentX + 3, contentY, contentWidth, contentHeight, -1, HPos.LEFT, VPos.CENTER);
        layoutInArea(background, contentX, contentY, contentWidth, contentHeight, -1, HPos.LEFT, VPos.CENTER);
    }

    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computeMaxHeight(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefHeight(height, topInset, rightInset, bottomInset, leftInset);
    }
}
