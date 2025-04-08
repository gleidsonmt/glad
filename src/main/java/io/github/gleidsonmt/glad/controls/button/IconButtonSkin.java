/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.glad.controls.button;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/09/2022
 */
@Deprecated
public class IconButtonSkin extends SkinBase<FabButton> {

    private final FabButton control;

    private final Rectangle clip = new Rectangle();
    private final SVGIcon iconContainer = new SVGIcon();

    private final Timeline timeline = new Timeline();
    private final Circle circle = new Circle();

    double radi = 0;

    public IconButtonSkin(FabButton _control) {
        super(_control);
        this.control = _control;

        if (_control.getIcon() == null || _control.getIcon() == Icon.NONE) {
            iconContainer.setContent(Icon.FAVORITE);
        } else
            iconContainer.setContent(_control.getIcon());

        _control.iconProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue != Icon.NONE) {
                iconContainer.setContent(newValue);
            }
        });

//        iconContainer.setFill(Color.WHITE);
        getChildren().add(iconContainer);

        this.clip.widthProperty().bind(this.control.widthProperty());
        this.clip.heightProperty().bind(this.control.heightProperty());

        for (BorderStroke stroke : this.control.getBorder().getStrokes()) {
            radi = stroke.getRadii().getTopLeftVerticalRadius();
        }

        _control.addEventHandler(MouseEvent.MOUSE_RELEASED, onPressed);
        _control.setClip(clip);

    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        layoutInArea(iconContainer, contentX, contentY, contentWidth, contentHeight, -1, HPos.CENTER, VPos.CENTER);
    }

    private final EventHandler<MouseEvent> onPressed = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {

            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return;
            }

            circle.setRadius(10);
            circle.setStrokeWidth(0);

            circle.setFill(Color.WHITE);

            circle.setLayoutX(event.getX());
            circle.setLayoutY(event.getY());

            circle.setOpacity(0.5);

            circle.setMouseTransparent(true);
            circle.setManaged(false);

            for (BorderStroke stroke : control.getBorder().getStrokes()) {
//                double arcWidth =
//                        stroke.getRadii().getTopLeftHorizontalRadius()
//                        + stroke.getRadii().getTopLeftVerticalRadius()
//                        + stroke.getRadii().getTopRightHorizontalRadius()
//                        + stroke.getRadii().getTopLeftVerticalRadius();

                double arcWidth = stroke.getRadii().getTopLeftVerticalRadius() +  stroke.getRadii().getTopLeftVerticalRadius();


                clip.setArcWidth(arcWidth);
                clip.setArcHeight(arcWidth);
            }


//            clip.setArcWidth( Math.floor(control.getWidth() / 6 ));
//            clip.setArcHeight( Math.floor(control.getHeight() / 6 ));

            getChildren().add(circle);


            double diameter = Math.max(control.getWidth(), control.getHeight());
            double radius = diameter / 2;

            timeline.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(circle.radiusProperty(), 0)),
                    new KeyFrame(Duration.millis(250), new KeyValue(circle.radiusProperty(), radius *2 ))
            );


            timeline.play();


            timeline.setOnFinished( (e) -> getChildren().remove(circle));
        }
    };

    protected double computeMaxWidth(double var1, double var3, double var5, double var7, double var9) {
        return this.getSkinnable().prefWidth(var1);
    }

    protected double computeMaxHeight(double var1, double var3, double var5, double var7, double var9) {
        return this.getSkinnable().prefHeight(var1);
    }

//    @Override
//    protected double computeMaxHeight(double v, double v1, double v2, double v3, double v4) {
//        return super.computePrefHeight(v, v1, v2, v3, v4);
//    }
//
//    @Override
//    protected double computeMaxWidth(double v, double v1, double v2, double v3, double v4) {
//        return super.computePrefWidth(v, v1, v2, v3, v4);
//    }
}
