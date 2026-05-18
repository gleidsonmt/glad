

package io.github.gleidsonmt.glad.controls.button;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  25/09/2022
 */
public class RippleButtonSkin extends ButtonSkin {

    private final Timeline timeline = new Timeline();
    private final Circle circle = new Circle();

    private final Rectangle clip = new Rectangle();

    private final Button control;

    public RippleButtonSkin(Button _control) {
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

                    double arcWidth = stroke.getRadii().getTopLeftVerticalRadius() + stroke.getRadii().getTopLeftVerticalRadius();

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

                    double arcWidth = stroke.getRadii().getTopLeftVerticalRadius() + stroke.getRadii().getTopLeftVerticalRadius();

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

            timeline.setOnFinished(_ -> getChildren().remove(circle));
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
