package io.github.gleidsonmt.glad.controls;

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

    Circle trigger = new Circle(10);

    protected ToggleSwitchSkin(ToggleSwitch control) {
        super(control);
        getChildren().add(pane);
        trigger.getStyleClass().add("trigger");
        trigger.setCenterX(2500);
        trigger.setCenterY(0);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);
        trigger.setLayoutX(-30);
//        trigger.getStyleClass().add("depth-1");


        Rectangle background = new Rectangle(50, 25);
        background.setCursor(Cursor.HAND);
        background.getStyleClass().add("foreground");
        background.setArcWidth(25);
        background.setArcHeight(25);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);
        getChildren().addAll(background, trigger);

        Color back = (Color) background.getFill();
        Color toColor = control.getColor();

        Color strokeInit = (Color) background.getStroke();

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);
        strokeTransition.setShape(background);

        on.bindBidirectional(control.onProperty());

        control.setOnMouseClicked(e -> {
            translateAnimation.setToX(
                    control.isOn() ? 0 : 23
            );

            fillAnimation.setFromValue(control.isOn() ? toColor : back );
            fillAnimation.setToValue(control.isOn() ? back : toColor );

            strokeTransition.setFromValue(control.isOn() ? toColor : strokeInit );
            strokeTransition.setToValue(control.isOn() ? strokeInit : toColor );
            translateAnimation.setOnFinished(_ -> {
                on.set(!on.get());
            });
            animation.play();
        });

        pane.setMinWidth(50);
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        layoutInArea(trigger, 3, 2, contentWidth, 20,1, HPos.LEFT, VPos.CENTER);
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
