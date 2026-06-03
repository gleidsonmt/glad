package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Drawer;
import io.github.gleidsonmt.glad.base.Root;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 02/06/2026
 */
public class DrawerImpl extends DialogAbstract<Drawer> implements Drawer {

    private Node content;

    public DrawerImpl(Root root) {
        super(root);
    }

    //
    @Override
    public void show() {

        root.block();

        root.flow()
                .content((Region) this.content)
                .with(this.with)
                .width(300)
                .height(-1)
                .pos(Pos.CENTER_LEFT)
                .anchor(Anchor.LEFT)
                .show();

        super.show();


        Timeline timeline = new Timeline();

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(content.translateXProperty(), -300)),
                new KeyFrame(Duration.millis(200),
                        new KeyValue(content.translateXProperty(), 0))
        );

        timeline.setRate(1);
        timeline.play();

        root.getForeground().addAction(_ -> {
            timeline.setRate(-1);
            timeline.play();

            timeline.setOnFinished(_ -> {
                hide();
            });
        });
    }

    @Override
    public Drawer content(Node content) {
        this.content = content;
        return this;
    }

    @Override
    public void hide() {
//        if (!isShowing()) return;
//        if (this.content == null) return;
        root.getChildren().remove(this.content);
        this.content.setTranslateX(0);
        StackPane.clearConstraints(this.content);
        this.width.set(-1);
        this.height.set(-1);
        super.hide();
    }
}
