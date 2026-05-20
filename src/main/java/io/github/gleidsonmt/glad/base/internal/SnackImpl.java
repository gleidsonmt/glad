

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.snack.Snack;
import io.github.gleidsonmt.glad.base.dialog.snack.SnackOption;
import io.github.gleidsonmt.glad.controls.button.Button;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.util.Duration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  13/08/2025
 */
public class SnackImpl extends FlowItemAbstract<Snack> implements Snack {

    private final Root root;
    private String message;
    private Node graphic;

    private List<SnackOption> actions;

    public SnackImpl(Root root) {
        this.root = root;
    }

    @Override
    public void show() {
        var bar = new SnackBar(this.message);
        if (graphic != null) bar.setGraphic(graphic);
        if (actions != null) {
            var ref = new Object() {
                int count = graphic != null ? 2 : 1;
            };
            actions.forEach(item -> {
                Button button = new Button(item.message());
                button.setOnAction(item.action());
                button.getStyleClass().addAll("btn-outlined", "snack-action");
                bar.add(button, ref.count++, 0);
                bar.add(new Separator(Orientation.VERTICAL), ref.count++, 0);
            });
            bar.getChildren().removeLast();

        }
        final Timeline timeline = new Timeline();

        TimerTask hideSnack = new TimerTask() {
            @Override
            public void run() {
                timeline.setRate(-1);
                timeline.play();
                timeline.setOnFinished(_ -> root.flow().remove(bar));
            }
        };

        Timer timer = new Timer();
        timer.schedule(hideSnack, 3000);

        root.flow()
                .pos(Pos.BOTTOM_CENTER)
                .width(-1)
                .height(-1)
                .content(bar)
                .insets(new Insets(20))
                .show();

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(bar.translateYProperty(), bar.getPrefHeight())),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(bar.translateYProperty(), 0))
        );

        timeline.setRate(1);
        timeline.play();
        reset();
    }

    private void reset() {
        this.graphic = null;
        this.actions = null;
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

    @Override
    public final Snack action(SnackOption... actions) {
        this.actions = List.of(actions);
        return this;
    }
}