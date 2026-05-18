

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.ComponentSkin;
import io.github.gleidsonmt.glad.controls.text_box.Editor;
import io.github.gleidsonmt.glad.controls.text_box.FloatEditor;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  10/09/2022
 */
public class TextBoxBaseSkin extends SkinBase<TextBoxBase> implements ComponentSkin<TextBoxBase> {

    //Fixed
    protected final TextBoxBase control;

    protected Label helperLabel;
    private final Timeline timeline = new Timeline();

    protected TextBoxBaseSkin(TextBoxBase _control) {
        super(_control);
        this.control = _control;

//        if (_control.isAnimate()) {
//            _control.setEditor(new FloatEditor());
//            pseudoClassStateChanged(PseudoClass.getPseudoClass("animate"), true);
//        } else {
//            _control.setEditor(new Editor());
//            pseudoClassStateChanged(PseudoClass.getPseudoClass("animate"), false);
//        }

        if (_control.getEditor() != null) {
            _control.getEditor().textProperty().bindBidirectional(_control.textProperty());
            _control.getEditor().promptTextProperty().bindBidirectional(_control.promptTextProperty());
            _control.getEditor().maskTextProperty().bindBidirectional(_control.maskTextProperty());
        }

        control.editorProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                if (!getChildren().contains(newValue)) {
                    getChildren().add(newValue);
//                    newValue.setFakeFocus(true);
                }
            }
        });

        if (control.getEditor() != null) {
            if (!getChildren().contains(control.getEditor()))
                getChildren().add(control.getEditor());
        }


        if (control.getRightNode() != null) {
            control.getRightNode().setManaged(false);
            getChildren().add(_control.getRightNode());
        }

        control.leftNodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getChildren().add(1, newValue);
                newValue.setManaged(false);
            }
        });

        control.rightNodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                getChildren().remove(oldValue);
            } else {
                if (!getChildren().contains(newValue))
                    getChildren().add(getChildren().size(), newValue);
            }
        });

        if (control.getLeftNode() != null) {
            control.getLeftNode().setManaged(false);
            getChildren().add(_control.getLeftNode());
        }

        registerChangeListener(_control.focusWithinProperty(), c -> {

            if ((boolean) c.getValue()) {
                _control.getEditor().requestFocus();
            } else {
                _control.setFocusTraversable(false);
            }

        });

        setInitialState(control);
    }


    private void configHelperLabel() {
        if (!getChildren().contains(helperLabel)) {
            helperLabel = new Label();
            helperLabel.getStyleClass().add("helper-label");
            getChildren().add(helperLabel);
            helperLabel.setMouseTransparent(true);
            helperLabel.setManaged(false);
            helperLabel.setOpacity(0);
            helperLabel.setFocusTraversable(false);
            helperLabel.setPrefWidth(Double.MAX_VALUE);
//            helperLabel.setWrapText(true);
            setAnimatedValidate();

        }
    }


    private void setAnimatedValidate() {
        double aniV = 200;
        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        helperLabel.opacityProperty(), 0.0
                )),
                new KeyFrame(Duration.millis(aniV), new KeyValue(
                        helperLabel.opacityProperty(), 1.0
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        helperLabel.translateYProperty(), 15
                )),
                new KeyFrame(Duration.millis(aniV), new KeyValue(
                        helperLabel.translateYProperty(), 0
                ))
        );

    }


    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
//        super.layoutChildren(x, y, w, h);
//
        double edX;
        double edW;

        if (getChildren().contains(control.getRightNode()) && getChildren().contains(control.getLeftNode())) {

            edX = x + control.getLeftNode().getBoundsInLocal().getWidth();
            edW = w - (control.getRightNode().getBoundsInLocal().getWidth() +
                       control.getLeftNode().getBoundsInLocal().getWidth());

        } else if (getChildren().contains(control.getLeftNode())) {

            edX = x + control.getLeftNode().getBoundsInLocal().getWidth();
            edW = w - control.getLeftNode().getBoundsInLocal().getWidth();

        } else if (getChildren().contains(control.getRightNode())) {

            edX = x;
            edW = w - control.getRightNode().getBoundsInLocal().getWidth();

        } else {

            edX = x;
            edW = w;

        }

        if (getChildren().contains(control.getEditor())) {
            positionInArea(control.getEditor(), edX, y, edW, h, -1, HPos.LEFT, VPos.CENTER);
        }

        if (getChildren().contains(control.getLeftNode()))
            layoutInArea(control.getLeftNode(), x, y, w, h, -1, HPos.LEFT, VPos.CENTER);

        if (getChildren().contains(control.getRightNode())) {
            layoutInArea(control.getRightNode(), x, y, w, h, -1, HPos.RIGHT, VPos.CENTER);
        }

        if (getChildren().contains(helperLabel)) {
            layoutInArea(helperLabel, x, y, w, h, -1, HPos.LEFT, VPos.BOTTOM);
            helperLabel.relocate(x, h + 5);
            helperLabel.requestLayout();
        }

        if (control.getEditor() != null)
            control.getEditor().resize(edW, control.getHeight());

        bind(control);
    }

    @Override
    public void bind(TextBoxBase _control) {

        registerChangeListener(_control.helperTextProperty(), e -> {
            String test = (String) e.getValue();
            if (test != null && !test.isBlank()) {
                configHelperLabel();
            }
        });

        registerChangeListener(control.helperTextProperty(), e -> {
            if (e.getValue() != null) {
                helperLabel.setText((String) e.getValue());
            }
        });
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    public void setInitialState(TextBoxBase _control) {
        if (control.getHelperText() != null && !control.getHelperText().isEmpty() && !control.getHelperText().isBlank()) {
            configHelperLabel();
            helperLabel.textProperty().bindBidirectional(_control.helperTextProperty());
        }

//        validate(control.isValid());

        // Validates control on valid property change
        registerChangeListener(_control.validProperty(), c -> {
            if (c.getValue() == null) return;
            validate((boolean) c.getValue());
        });

    }

    public void validate(boolean invalidate) {
        if (!invalidate) {
            timeline.play();
        } else {
            if (helperLabel != null)
                helperLabel.setOpacity(0.0);
        }
    }
}
