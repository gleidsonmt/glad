package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.FlowItemAbstract;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.Snack;
import io.github.gleidsonmt.glad.base.SnackBar;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/08/2025
 */
public class SnackImpl extends FlowItemAbstract<Snack> implements Snack {

    private final SnackBar snackBar;
    private final Root root;

    private final Timeline timeline = new Timeline();

    public SnackImpl(Root root, String message) {
        this.root = root;
        this.snackBar = new SnackBar(message);
    }

    @Override
    public void show() {
        snackBar.build();

        TimerTask hideSnack = new TimerTask() {
            @Override
            public void run() {
                timeline.setRate(-1);
                timeline.play();
                timeline.setOnFinished(_ -> hide());
            }
        };

        Timer timer = new Timer();
        timer.schedule(hideSnack, 3000);

        root.flow()
                .pos(Pos.BOTTOM_CENTER)
                .content(snackBar)
                .anchor(Anchor.BOTTOM)
                .insets(new Insets(20))
                .show();

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(snackBar.translateYProperty(), snackBar.getBoundsInParent().getHeight(), Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(snackBar.translateYProperty(), 0, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.ZERO,
                        new KeyValue(snackBar.opacityProperty(), 0, Interpolator.EASE_IN)),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(snackBar.opacityProperty(), 1, Interpolator.EASE_IN))
        );
        timeline.setRate(1);
        timeline.play();
    }

    @Override
    public void hide() {
        Platform.runLater(() -> root.flow()
                .remove(snackBar));
    }
}
