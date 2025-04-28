package io.github.gleidsonmt.glad.controls.button;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class SmoothButtonSkin extends AnimatedButtonSkin {

    public SmoothButtonSkin(AnimatedButton control) {
        super(2, control);

        getSkinnable().setOnMouseExited(event -> {
            timeline.setRate(-1);
            timeline.play();
        });

        oldTextFill = control.getTextFill();

        shapes.forEach(rect -> {
            rect.setPrefHeight(0);
            rect.setMaxHeight(0);

            rect.setPrefWidth(Region.USE_COMPUTED_SIZE);
            rect.setMaxWidth(Region.USE_COMPUTED_SIZE);

        });

        getSkinnable().setOnMouseEntered(event -> {
            timeline.getKeyFrames().clear();
            timeline.setRate(1);
            shapes.forEach(rect -> {

                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), 0)),
                        new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), 0)),
                        new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), oldTextFill)),

                        new KeyFrame(velocity.get(), new KeyValue(rect.prefHeightProperty(), getSkinnable().getHeight())),
                        new KeyFrame(velocity.get(), new KeyValue(rect.maxHeightProperty(), getSkinnable().getHeight())),

                        new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), control.getTransitionTextFill()))

                );
            });


//            if (timeExited.getStatus() == Animation.Status.RUNNING) {
//                timeExited.stop();
//            }

            if (timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.playFromStart();
                return;
            }

            timeline.play();

        });
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInArea(shapes.get(0), x, y, w, h, 0,
                HPos.CENTER, VPos.TOP);

        layoutInArea(shapes.get(1), x, y, w, h, 0,
                HPos.CENTER, VPos.BOTTOM);

    }
}
