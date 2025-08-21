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
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/08/2025
 */
public class SnackImpl extends FlowItemAbstract<Snack> implements Snack {

    private final Root root;
    private String message;
    private Node graphic;

    public SnackImpl(Root root) {
        this.root = root;
    }

    @Override
    public void show() {
        var bar = new SnackBar(this.message);
        if (graphic != null) bar.setGraphic(graphic);
        final Timeline timeline = new Timeline();

        TimerTask hideSnack = new TimerTask() {
            @Override
            public void run() {
                timeline.setRate(-1);
                timeline.play();
                timeline.setOnFinished(_ ->  root.flow().remove(bar));
            }
        };

        Timer timer = new Timer();
        timer.schedule(hideSnack, 3000);

        root.flow()
                .pos(Pos.BOTTOM_CENTER)
                .content(bar)
                .anchor(Anchor.BOTTOM)
                .insets(new Insets(20))
                .show();


        bar.applyCss();
        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(bar.translateYProperty(), bar.prefHeight(-1))),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(bar.translateYProperty(), 0))
        );

        timeline.setRate(1);
        timeline.play();
        reset();
    }

    private void reset() {
        this.graphic = null;
    }

    @Override
    public void hide() {
        root.flow().remove(root.getChildren().removeLast());
//        Platform.runLater(() -> root.flow()
//                .remove(snackBar));
    }

    @Override
    public Snack message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Snack graphic(Node graphic) {
        this.graphic = graphic;
        return this;
    }
}
