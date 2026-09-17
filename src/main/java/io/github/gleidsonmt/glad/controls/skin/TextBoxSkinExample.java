package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.ComponentSkin;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.text_box.TextBoxExample;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 07/07/2026
 */
@Deprecated(forRemoval = true)
public class TextBoxSkinExample extends SkinBase<TextBoxExample> implements ComponentSkin<TextBoxExample> {

    private IconButton buttonAction;

    protected Label label = createLabel();
    protected Label helper = createHelperText();
    protected Label count = new Label("0/0");

    private final double aniVeloz = 100;

    private final Timeline upAnimation = new Timeline();
    private final Timeline downAnimation = new Timeline();

    private boolean isOnTop = false;

    private final Timeline helperAnimation = new Timeline();

    protected final TextBoxBase textBox;

    private final TextBoxExample control;
    private TextField editor;

    public TextBoxSkinExample(TextBoxExample _control, boolean mask) {
        super(_control);
        this.editor = !mask ? new TextField() : new PasswordField();

        if (mask) {
            this.editor = new PasswordField();
            this.editor.setSkin(new EditorSkin(this.editor));
        } else {
            this.editor = new TextField();
        }
        this.editor.getStyleClass().addAll("editor");
        this.textBox = new TextBoxBase(editor);

//        if (!mask) new TextBoxBehavior(textBox);
//        else new PasswordBoxBehavior(textBox);

        this.control = _control;

        var region = new Region();
        region.getStyleClass().add("helper-icon");
        helper.setGraphic(region);

        label.setStyle("-fx-background-color: red;");
        control.setStyle("-fx-background-color: blue, green; -fx-border-width: 2px; -fx-border-color: yellow;");

//        textBox.setLeftNode(new SVGIcon(Icon.FACE));

//        this.clearButton = new IconButton(new SVGIcon(Icon.CLEAR), true);
//        createAction(mask);

//        configAction(_control);

//        count.setManaged(false);

        pseudoClassStateChanged(PseudoClass.getPseudoClass("animate"), _control.isAnimate());

        bind(_control);
        setInitialState(_control);

        getChildren().addAll(textBox, label);

        ChangeListener<Number> hideAction = (_, _, newVal) -> {
            textBox.setRightNode(newVal.intValue() > 0 ? this.buttonAction : null);
        };

//        if (_control.isAction()) _control.getEditor().lengthProperty().addListener(hideAction);

//        _control.editorProperty().addListener((_, oldVal, newVal) -> {
//            if (newVal != null) {
//                newVal.lengthProperty().addListener(hideAction);
//            } else {
//                if (oldVal != null) {
//                    oldVal.lengthProperty().removeListener(hideAction);
//                }
//            }
//        });

        _control.actionProperty().addListener((_, _, newValue) -> {
            textBox.setRightNode(newValue ? this.buttonAction : null);
        });

        registerChangeListener(_control.maxProperty(), c -> {
            if ((int) c.getValue() > 0) {
                addCount();
            }
        });

        registerChangeListener(_control.helperTextProperty(), c -> {
            if (c.getValue() != null && !((String) c.getValue()).isEmpty() && !getChildren().contains(helper)) {
                getChildren().add(helper);
            }
        });

        registerChangeListener(_control.validProperty(), c -> {
            if (c.getValue() == null) return;
            validate((boolean) c.getValue());
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

        setAnimatedValidate();

    }

    public void validate(boolean valid) {
        if (helper.getText() == null || helper.getText().isEmpty()) return;

//        if (getChildren().contains(helper)) return;
//        else
//            getChildren().add(helper);
        if (!getChildren().contains(helper)) {
            getChildren().add(helper);
        }

        if (!valid) {
            helperAnimation.play();
        } else {
            if (helper != null)
                helper.setOpacity(0.0);
        }
    }

    private Label createHelperText() {
        var helper = new Label();
        helper.setManaged(false);
        helper.setAlignment(Pos.BASELINE_LEFT);

        helper.getStyleClass().add("helper-label");
        helper.setMouseTransparent(true);
        helper.setManaged(false);
        helper.setOpacity(0);
        helper.setFocusTraversable(false);
        helper.setPrefWidth(Double.MAX_VALUE);
        helper.setWrapText(true);

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

    private void addCount() {
//        count.textProperty().unbind();
        count.textProperty().bind(textBox.getEditor().lengthProperty().asString("%d").concat("/").concat(control.maxProperty().asString("%d")));

//        control.g
//        textBox.getEditor().setTextFormatter(new TextFormatter<>(change -> {
//            if (change.getControlNewText().length() > control.getMax()) {
//                return null; // Reject the change
//            }
//            return change; // Accept the change
//        }));

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
        double leftNodeWidth = textBox.getLeftNode() != null ? textBox.getLeftNode().getLayoutBounds().getWidth() : 0;
        var spacing = textBox.getEditor().getPadding().getLeft();

        if (leftNodeWidth > 0) {
            downAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            label.translateXProperty(), -(leftNodeWidth + spacing)
                    )),

                    new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                            label.translateXProperty(), 0
                    ))
            );
        }

        pseudoClassStateChanged(PseudoClass.getPseudoClass("animate"), false);

        downAnimation.setOnFinished(e -> {
            isOnTop = false;
        });
//        }
        downAnimation.play();
    }


    private void up() {

        if (!control.isAnimate()) return;

        double leftNodeWidth = textBox.getLeftNode() != null ? textBox.getLeftNode().getLayoutBounds().getWidth() : 0;
        var spacing = editor.getPadding().getLeft();

        upAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateYProperty(), 0
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateYProperty(), ((textBox.getHeight() / 2) * -1)

                )),


                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.fontProperty(), label.getFont()
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.fontProperty(), new Font(11)
                ))
        );
        if (leftNodeWidth > 0) {
            upAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            label.translateXProperty(), 0
                    )),

                    new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                            label.translateXProperty(), -(leftNodeWidth + spacing)
                    ))
            );
        }


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

    private double getSpacing() {
        return textBox.getSpacing();
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        // 1. Adjust the available width by stripping horizontal insets
        double contentWidth = width - leftInset - rightInset;

        // 2. Query your child or content node's minimum height using the adjusted width
        double contentMinHeight = textBox.minHeight(contentWidth);

//        if (!control.isAnimate()) {
//            contentMinHeight += label.minHeight(width);
//        }
        // 3. Sum up the vertical insets and content minimum height
        return topInset + contentMinHeight + bottomInset
               + label.minHeight(width);

//        System.out.println("insets " + topInset);
//        return textBox.minHeight(width) + (topInset + bottomInset)
//               + helper.minHeight(width)
//                + (control.isAnimate() ? 0 : label.getHeight() )
////               label.minHeight(width) + topInset + bottomInset
////               + count.minHeight(width)
//                ;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return textBox.prefHeight(width) + topInset + bottomInset
               + (textBox.getHeight() / 2 * -1)
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

    private double calculateHeight() {
        return 0;
    }

//    @Override
//    protected void layoutChildren(double x, double y, double w, double h) {
//        super.layoutChildren(x, y, w, h);
////        layoutInArea(label, x , y, w, h, -(label.getHeight()/2), HPos.LEFT, VPos.BASELINE);
//        layoutInArea(label, x , y, w, h, -1, HPos.LEFT, VPos.BASELINE);
//    }



    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

//
        double leftNodeWidth = textBox.getLeftNode() != null ? textBox.getLeftNode().getLayoutBounds().getWidth() : 0;
//        double spacing = textBox.getLeftNode() != null ? textBox.getEditor().getPadding().getLeft() + getSpacing() : getSpacing();
        double spacing = textBox.getEditor().getPadding().getLeft();

        layoutInArea(textBox, x, y, w, h - getMinHelperHeight(w), -1, HPos.LEFT, VPos.CENTER);

        layoutInArea(helper, x + textBox.getPadding().getLeft(), y, w - (count.getWidth() + textBox.getPadding().getLeft()), h
                ,
                -1, HPos.LEFT, VPos.BOTTOM);

        if (control.isAnimate()) {

            if (control.getText() != null && !control.getText().isBlank()) {
    //                label.setTranslateY((textBox.getHeight() / 2 * -1));
    //                label.setTranslateX(-(leftNodeWidth) + spacing);
            }

            if (isOnTop && control.getText() != null && !control.getText().isBlank()) {
                layoutInArea(label, leftNodeWidth + control.getPadding().getLeft() + spacing,
                        y, w,
                        h - getMinHelperHeight(w), -1, HPos.LEFT, VPos.CENTER);
            } else {
                layoutInArea(label, leftNodeWidth + (textBox.getPadding().getLeft()) - label.getPadding().getLeft(),
                        y, w,
                        h - getMinHelperHeight(w), -1, HPos.LEFT, VPos.CENTER);
            }
        } else {
            layoutInArea(label, x , y - (label.getHeight() / 2), w, h, -1, HPos.LEFT, VPos.TOP);
        }

        layoutInArea(count, x, y, w,
                h , -1, HPos.RIGHT, VPos.BOTTOM);

    }

    private double getMinHelperHeight(double w) {
        if (getChildren().contains(helper)) {
            return helper.minHeight(w);
        } else if (getChildren().contains(count)) {
            return count.minHeight(w);
        } else return 0;
    }

    @Override
    public void bind(TextBoxExample _control) {

        textBox.promptTextProperty().bindBidirectional(_control.promptTextProperty());
        textBox.leftNodeProperty().bindBidirectional(_control.iconProperty());

//        textBox.leftNodeProperty().bindBidirectional(_control.leftNodeProperty());
//        helper.visibleProperty().bind(_control.validProperty().not());

//        textBox.getEditor().textProperty().bindBidirectional(_control.textProperty());
        textBox.getEditor().textProperty().bindBidirectional(_control.textProperty());

        helper.textProperty().bindBidirectional(_control.helperTextProperty());
        label.textProperty().bindBidirectional(control.labelProperty());
    }

    @Override
    public void setInitialState(TextBoxExample c) {
        if (control.getMax() > 0) {
            getChildren().add(count);
            addCount();
        }

        if (control.getHelperText() != null && !control.getHelperText().isEmpty()) {
            getChildren().add(helper);
        }
    }

    private void setAnimatedValidate() {
        double aniV = 200;
        helperAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        helper.opacityProperty(), 0.0
                )),
                new KeyFrame(Duration.millis(aniV), new KeyValue(
                        helper.opacityProperty(), 1.0
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        helper.translateYProperty(), 15
                )),
                new KeyFrame(Duration.millis(aniV), new KeyValue(
                        helper.translateYProperty(), 0
                ))
        );

    }
}
