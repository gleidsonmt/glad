package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.ComponentSkin;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.Editor;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 07/07/2026
 */
public class TextBoxSkin extends SkinBase<TextBoxBase> implements ComponentSkin<TextBoxBase> {

    private final IconButton clearButton;

    protected Label label = createLabel();
    protected Label helper = createHelperText();
    protected Label count = new Label("0/0");

    private final double aniVeloz = 100;

    private final Timeline upAnimation = new Timeline();
    private final Timeline downAnimation = new Timeline();

    private boolean isOnTop = false;

    private final TextBoxBase textBox = new TextBoxBase() {
        {
            var editor = new TextField();
            editor.getStyleClass().add("editor");
            setEditor(editor);
        }
    };

    private final TextBox control;

    private Label createHelperText() {
        var helper = new Label();
        helper.setManaged(false);
        helper.setAlignment(Pos.BASELINE_LEFT);
        return helper;
    }

    private Label createLabel() {
        var label = new Label();
        label.setManaged(false);
        label.setPrefHeight(20);
        label.setFont(new Font(12));

        label.setMouseTransparent(true);
        label.setWrapText(true);
        label.toFront();
        return label;
    }

    public TextBoxSkin(TextBox _control) {
        super(_control);
        this.control = _control;
        this.clearButton = new IconButton(new SVGIcon(Icon.CLEAR), true);
        configAction(_control);

        count.setManaged(false);


        pseudoClassStateChanged(PseudoClass.getPseudoClass("animate"), _control.isAnimate());


        bind(_control);
        setInitialState(_control);


        getChildren().addAll(textBox, label);

//        _control.actionProperty().addListener((_, _, newValue) -> textBox.setRightNode(newValue  ? createRightAction() : null));

        ChangeListener<Number> hideAction = (_, _, newVal) -> {
            _control.setRightNode(newVal.intValue() > 0 ? this.clearButton : null);
        };

//        if (_control.isAction()) _control.getEditor().lengthProperty().addListener(hideAction);

        _control.editorProperty().addListener((_, oldVal, newVal) -> {
            if (newVal != null) {
                newVal.lengthProperty().addListener(hideAction);
            } else {
                if (oldVal != null) {
                    oldVal.lengthProperty().removeListener(hideAction);
                }
            }
        });

        registerChangeListener(_control.maxProperty(), c -> {
            if ((int) c.getValue() > 0) {
                addCount();
            }
        });

        registerChangeListener(_control.helperTextProperty(), c -> {
            if (c.getValue() != null && !((String) c.getValue()).isEmpty()) {
                getChildren().add(helper);
            }
        });

        //      When focused start animation
        registerChangeListener(_control.focusWithinProperty(), c ->
        {
            if (!_control.isAnimate()) {
                return;
            }
            if ((boolean) c.getValue()) {
                if (_control.getText() == null || _control.getText().isEmpty()) {
                    up();
                }
            } else {

                if (_control.getText() == null) {
                    down();
                    return;
                }

                if (_control.getText().isEmpty() || _control.getText().isBlank()) {
                    down();
                }
            }
        });
    }

    private void addCount() {
        count.textProperty().unbind();
        count.textProperty().bind(textBox.getEditor().lengthProperty().asString("%d").concat("/").concat(control.maxProperty().asString("%d")));

        textBox.getEditor().setTextFormatter(new TextFormatter<>(change -> {
            // Check if the proposed new text length exceeds 10
            System.out.println(change.getControlNewText().length());
            if (change.getControlNewText().length() > control.getMax()) {

                return null; // Reject the change
            }

            return change; // Accept the change
        }));

    }

    private void down() {

        if (!control.isAnimate()) return;

        downAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateYProperty(), ((textBox.getHeight() / 2) * -1)
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateYProperty(), 0
                ))
        );
//
//        if (getSkinnable().getParent() instanceof NeoTextBox base) {
        downAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateXProperty(), -(textBox.getLeftNode().getLayoutBounds().getWidth() + getSpacing())
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateXProperty(), 0
                ))
        );
        pseudoClassStateChanged(PseudoClass.getPseudoClass("animate"), false);

        downAnimation.setOnFinished(e -> {
            isOnTop = false;
        });
//        }
        downAnimation.play();
    }


    private void up() {

        if (!control.isAnimate()) return;

        upAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateYProperty(), 0
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateYProperty(), ((textBox.getHeight() / 2) * -1)

                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateXProperty(), 0
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateXProperty(), -(textBox.getLeftNode().getLayoutBounds().getWidth() + getSpacing())
                ))
                ,
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.fontProperty(), label.getFont()
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.fontProperty(), new Font(11)
                ))
        );

//        if (getSkinnable().getParent() instanceof TextBoxBase base) {
//            upAnimation.getKeyFrames().addAll(
//                    new KeyFrame(Duration.ZERO, new KeyValue(
//                            label.translateXProperty(), 0
//                    )),
//
//                    new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
//                            label.translateXProperty(), -(control.getLeftNode().getBoundsInParent().getWidth())
//                    ))
//            );
//        }


//        control.pseudoClassStateChanged(FLOAT_PSEUDO_CLASS, true);
//        upAnimation.setOnFinished(event -> vPos = getPos());

        upAnimation.setOnFinished(e -> {
            pseudoClassStateChanged(PseudoClass.getPseudoClass("animate"), true);
            isOnTop = true;
        });

        upAnimation.play();

    }

    protected void configAction(TextBox control) {
        clearButton.setFocusTraversable(false);
        clearButton.setManaged(false);
        clearButton.setOnMouseClicked(createAction(control));
        clearButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
    }

    private @NotNull EventHandler<MouseEvent> createAction(TextBox control) {
        return _ -> {
            control.getEditor().clear();
            control.getEditor().requestFocus();
        };
    }

    private double getSpacing() {
        return textBox.getSpacing();
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return textBox.minHeight(width) + helper.minHeight(width) + label.minHeight(width) + topInset + bottomInset
                ;
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return textBox.minWidth(height) + label.getWidth() +
               getSpacing()
                ;
//        return super.computeMinWidth(height, topInset, rightInset, bottomInset, leftInset) + 140;
    }

    //    @Override
//    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return textBox.prefHeight(width) + helper.prefHeight(width) + label.prefHeight(width) + snappedTopInset() + snappedBottomInset()
//                ;
//
//    }

//    @Override
//    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return super.computeMaxHeight(width, topInset, rightInset, bottomInset, leftInset)
//                + 100
//
//                ;
//    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {

//        super.layoutChildren(x, y, w, h);

        layoutInArea(textBox, x, y, w, h - helper.getHeight(), -1, HPos.LEFT, VPos.CENTER);
        layoutInArea(helper, x + textBox.getPadding().getLeft(), y, w, h
                ,
                -1, HPos.LEFT, VPos.BOTTOM);


        if (control.isAnimate()) {

            if (control.getText() != null && !control.getText().isBlank()) {
                label.setTranslateY((textBox.getHeight() / 2 * -1));
                label.setTranslateX(-(textBox.getLeftNode().getLayoutBounds().getWidth()) - getSpacing());
            }

            if (isOnTop && control.getText() != null && !control.getText().isBlank()) {
                layoutInArea(label, textBox.getLeftNode().getLayoutBounds().getWidth() + textBox.getInsets().getLeft() + (getSpacing()),
                        y, w,
                        h - helper.minHeight(w), -1, HPos.LEFT, VPos.CENTER);

            } else {

                layoutInArea(label, textBox.getLeftNode().getLayoutBounds().getWidth() + textBox.getInsets().getLeft() + getSpacing(),
                        y, w,
                        h - helper.minHeight(w), -1, HPos.LEFT, VPos.CENTER);

            }

        } else {
            layoutInArea(label, x, y - getSpacing(), w, h + helper.minHeight(w), -1, HPos.LEFT, VPos.BASELINE);
        }

        layoutInArea(count, x, y, w,
                h, -1, HPos.RIGHT, VPos.BOTTOM);
    }

    @Override
    public void bind(TextBoxBase _control) {
        textBox.leftNodeProperty().bindBidirectional(_control.leftNodeProperty());

        textBox.getEditor().textProperty().bindBidirectional(_control.textProperty());
        helper.textProperty().bindBidirectional(_control.helperTextProperty());
        label.textProperty().bind(control.labelProperty());

    }

    @Override
    public void setInitialState(TextBoxBase c) {
        if (control.getMax() > 0) {
            getChildren().add(count);
            addCount();
        }

        if (control.getHelperText() != null && !control.getHelperText().isEmpty()) {
            getChildren().add(helper);
        }

    }
}