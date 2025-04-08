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

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/09/2022
 */
public class ButtonSkin extends javafx.scene.control.skin.ButtonSkin {

    private final Timeline timeline = new Timeline();
    private final Circle circle = new Circle();

    private final Rectangle clip = new Rectangle();

    private final Button control;

    public ButtonSkin(Button _control) {
        super(_control);
        this.control = _control;

        this.clip.widthProperty().bind(control.widthProperty());
        this.clip.heightProperty().bind(control.heightProperty());

        EventHandler<MouseEvent> onPressed = event -> {

            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return;
            }

            circle.setRadius(0);
            circle.setStrokeWidth(0);

            circle.setLayoutX(event.getX());
            circle.setLayoutY(event.getY());

            circle.setOpacity(0.5);
            circle.setMouseTransparent(true);


            if (control.getBorder() != null) {
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

            } else if (control.getBackground() != null) {
                for (BackgroundFill stroke : control.getBackground().getFills()) {
//                double arcWidth =
//                        stroke.getRadii().getTopLeftHorizontalRadius()
//                        + stroke.getRadii().getTopLeftVerticalRadius()
//                        + stroke.getRadii().getTopRightHorizontalRadius()
//                        + stroke.getRadii().getTopLeftVerticalRadius();

                    double arcWidth = stroke.getRadii().getTopLeftVerticalRadius() +  stroke.getRadii().getTopLeftVerticalRadius();

                    clip.setArcWidth(arcWidth);
                    clip.setArcHeight(arcWidth);
                }
            }

            getChildren().add(circle);

            clip.setTranslateX(-(circle.getLayoutX()));
            clip.setTranslateY(-(circle.getLayoutY()));

            circle.setClip(clip);

            double diameter = Math.max(control.getWidth(), control.getHeight());
            double radius = diameter / 2;

            timeline.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(circle.radiusProperty(), 0)),
                    new KeyFrame(Duration.millis(250), new KeyValue(circle.radiusProperty(), radius * 2))
            );


            timeline.play();

            timeline.setOnFinished(e -> getChildren().remove(circle));
        };

        control.addEventFilter(MouseEvent.MOUSE_CLICKED, onPressed);

        circle.setFill(_control.getCircleFill());

        registerChangeListener((ObservableValue<?>) _control.circleFillProperty(), c -> {
            if (c.getValue() != null) {
                circle.setFill((Paint) c.getValue());
            }
        });
    }


}
