package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.text_box.FloatEditor;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/04/2025
 */
public class FloatEditorSkin extends EditorSkin {

    private final Label moveablePrompt = new Label("Float Prompt");
    private final double aniVeloz = 100;

    private final Timeline upAnimation = new Timeline();
    private final Timeline downAnimation = new Timeline();

    public FloatEditorSkin(FloatEditor _control) {
        super(_control);

        // Setting default Conventions
        moveablePrompt.textProperty().bind(_control.promptTextProperty());
        moveablePrompt.setFont(new Font(12));

        moveablePrompt.getStyleClass().add("moveable-prompt");
        moveablePrompt.setMouseTransparent(true);
        moveablePrompt.toFront();

        // add prompt and hide old prompt
        getChildren().add(moveablePrompt);
        setPromptTextFill(Color.TRANSPARENT);


        //      When focused start animation
        registerChangeListener(_control.focusedProperty(), c ->
        {

            if ((boolean) c.getValue()) {
                if (_control.getText() == null || _control.getText().isEmpty()) {
                    up();
                }
            } else {
                if (_control.getText() == null ) {
                    down();
                    return;
                }

                if (_control.getText().isEmpty()) {
                    down();
                }
            }
        });

    }

    private void down() {
        downAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.translateYProperty(), ((getSkinnable().getHeight() / 2) * -1)
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.translateYProperty(), 0
                ))
        );
//
        if (getSkinnable().getParent() instanceof TextBoxBase base) {
            downAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            moveablePrompt.translateXProperty(), -(base.getLeftNode().getBoundsInParent().getWidth())
                    )),

                    new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                            moveablePrompt.translateXProperty(), 0
                    ))
            );
        }
        downAnimation.play();
    }

    private void up() {

        upAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.translateYProperty(), 0
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.translateYProperty(), ((getSkinnable().getHeight() / 2) * -1)
                ))

//                new KeyFrame(Duration.ZERO, new KeyValue(
//                        moveablePrompt.translateXProperty(), 0
//                )),
//
//                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
//                        moveablePrompt.translateXProperty(),  -getSkinnable().getBoundsInParent().getMinX()
//                ))

//                new KeyFrame(Duration.ZERO, new KeyValue(
//                        moveablePrompt.fontProperty(), moveablePrompt.getFont()
//                )),
//
//                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
//                        moveablePrompt.fontProperty(), new Font(11)
//                ))
        );

        if (getSkinnable().getParent() instanceof TextBoxBase base) {
            upAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            moveablePrompt.translateXProperty(), 0
                    )),

                    new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                            moveablePrompt.translateXProperty(), -(base.getLeftNode().getBoundsInParent().getWidth())
                    ))
            );
        }


//        control.pseudoClassStateChanged(FLOAT_PSEUDO_CLASS, true);
//        upAnimation.setOnFinished(event -> vPos = getPos());

        upAnimation.play();


    }

    private boolean isAnimation() {
        return upAnimation.getStatus() == Animation.Status.RUNNING || downAnimation.getStatus() == Animation.Status.RUNNING;
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        if (!isAnimation()) {
            if (getSkinnable().getText() != null && !getSkinnable().getText().isEmpty()) {
                positionInArea(moveablePrompt, x,
                        (y - moveablePrompt.getTranslateY() +
                         getSkinnable().getInsets().getTop() + getSkinnable().getInsets().getBottom()),
                        w, h, -1, HPos.LEFT, VPos.BASELINE);
            } else {
                positionInArea(moveablePrompt, x,
                        y, w, h, -1, HPos.LEFT, VPos.CENTER);
            }
        } else {
            positionInArea(moveablePrompt, x,
                    y, w, h, -1, HPos.LEFT, VPos.CENTER);
        }

    }
}
