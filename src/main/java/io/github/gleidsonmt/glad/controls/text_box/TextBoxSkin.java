package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.skin.PasswordBoxBehavior;
import io.github.gleidsonmt.glad.controls.skin.TextBoxBehavior;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 24/08/2026
 */
public class TextBoxSkin extends SkinBase<TextBox> {

    private final Label label = new Label();
    private final Label suggestedText = new Label(null);

    private Label countText = new Label();
    private TextField editor = new TextField();
    private HBox container = new HBox();

    private Timeline animation = new Timeline();
    private double aniVeloz = 200;
    //    private Node icon = new SVGIcon(Icon.FACE);
//    private Node action = new SVGIcon(Icon.CLEAR);

    public boolean top = false;
    private double labelScale = 0.90;

    protected TextBoxSkin(TextBox control, TextField editor) {
        super(control);
        getChildren().addAll(container);

        if (control.getLabel() != null && !control.getLabel().isEmpty() && !control.getLabel().isBlank()) {
            getChildren().addAll(label);
        }

        container.getChildren().addAll(editor);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPadding(new Insets(0, 10, 0, 10));
        container.setSpacing(5);

        label.setLabelFor(editor);
        HBox.setHgrow(editor, Priority.ALWAYS);

        if (control.isActionable() && editor instanceof PasswordField)
            new PasswordBoxBehavior(this, editor);
        else
            new TextBoxBehavior(this, editor);
        ;

        initialSate();
//        container.setMouseTransparent(true);

        registerChangeListener(control.focusWithinProperty(), c -> {

            if (!control.isAnimate()) return;

            if ((boolean) c.getValue()) {
                editor.requestFocus();
            }

            if ((boolean) c.getValue()) {
                if (editor.getText() == null || editor.getText().isEmpty()) {
                    up();
                }
            } else {
                if (editor.getText() == null) {
                    down();
                    return;
                }

                if (editor.getText().isEmpty() || editor.getText().isBlank()) {
                    down();
                }
            }
        });


        editor.setMaxHeight(Double.MAX_VALUE);
        container.getStyleClass().add("background");
        editor.getStyleClass().add("editor");
//        control.setStyle("-fx-border-color: -medium-gray; -fx-border-width: 1px;");

        registerListeners();
    }

    private void registerListeners() {
        registerChangeListener(getSkinnable().suggestedProperty(), c -> {
            String text = getSkinnable().getSuggested();
            suggestedText.setText(text);

            if (text != null && !text.isEmpty()) {
                if (!getChildren().contains(suggestedText)) {
                    getChildren().add(suggestedText);
                }
            } else {
                getChildren().remove(suggestedText);
            }
        });

        registerChangeListener(getSkinnable().maxProperty(), c -> {
            if (((int) c.getValue()) > 0) {
                if (!getChildren().contains(countText)) {
                    getChildren().add(countText);
                }
            } else {
                getChildren().remove(countText);
            }
        });

        getSkinnable().iconProperty().addListener((_, oldValue, newValue) -> {
            if (oldValue != null)
                container.getChildren().remove(oldValue);
            if (newValue != null)
                container.getChildren().addFirst(newValue);
        });

    }

    private void initialSate() {
        label.setFocusTraversable(false);
        label.setMouseTransparent(true);
        label.textProperty().bind(getSkinnable().labelProperty());

        if (getSkinnable().getIcon() != null) {
            container.getChildren().addFirst(getSkinnable().getIcon());
            getSkinnable().getIcon().setFocusTraversable(false);
        }

        if (getSkinnable().getSuggested() != null) {
            suggestedText.setText(getSkinnable().getSuggested());
            getChildren().add(suggestedText);
        }

        suggestedText.getStyleClass().add("suggested-text");
        countText.getStyleClass().add("count-text");
        suggestedText.setFocusTraversable(false);
        countText.setFocusTraversable(false);

        countText.textProperty().bind(editor.lengthProperty().asString("%d").concat("/").concat(getSkinnable().maxProperty().asString("%d")));

    }

    private static final double BOTTOM_GAP = 4.0;

    private double computeTopSpace(double width) {
        if (!label.isVisible() && (label.getText() == null || label.getText().isEmpty())) {
            return 0;
        }
        return getSkinnable().isAnimate()
                ? snapSizeY(label.prefHeight(width)) / 2.0
                : snapSizeY(label.prefHeight(width));
    }

    private double computeBottomSpace(double width) {
        double extra = 0;
        if (getChildren().contains(suggestedText) && getChildren().contains(countText)) {
            double val = Math.max(suggestedText.prefHeight(width), countText.prefHeight(width));
            extra += snapSizeY(val) + snapSizeY(BOTTOM_GAP);
        } else if (getChildren().contains(suggestedText)) {
            extra += snapSizeY(suggestedText.prefHeight(width)) + snapSizeY(BOTTOM_GAP);
        } else if (getChildren().contains(countText)) {
            extra += snapSizeY(countText.prefHeight(width)) + snapSizeY(BOTTOM_GAP);
        }
        return extra;
    }

    private double computeMinBottomSpace(double width) {
        double extra = 0;
        if (getChildren().contains(suggestedText) && getChildren().contains(countText)) {
            double val = Math.max(suggestedText.minHeight(width), countText.minHeight(width));
            extra += snapSizeY(val) + snapSizeY(BOTTOM_GAP);
        } else if (getChildren().contains(suggestedText)) {
            extra += snapSizeY(suggestedText.minHeight(width)) + snapSizeY(BOTTOM_GAP);
        } else if (getChildren().contains(countText)) {
            extra += snapSizeY(countText.minHeight(width)) + snapSizeY(BOTTOM_GAP);
        }

        return extra;
    }

    private double computeMaxBottomSpace(double width) {
        double extra = 0;
        if (getChildren().contains(suggestedText) && getChildren().contains(countText)) {
            double val = Math.max(suggestedText.maxHeight(width), countText.maxHeight(width));
            extra += snapSizeY(val) + snapSizeY(BOTTOM_GAP);
        } else if (getChildren().contains(suggestedText)) {
            extra += snapSizeY(suggestedText.maxHeight(width)) + snapSizeY(BOTTOM_GAP);
        } else if (getChildren().contains(countText)) {
            extra += snapSizeY(countText.maxHeight(width)) + snapSizeY(BOTTOM_GAP);
        }

        return extra;
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return leftInset + rightInset + container.prefWidth(height);
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return leftInset + rightInset + container.minWidth(height);
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return topInset + bottomInset
               + computeTopSpace(width)
               + container.prefHeight(width)
               + computeBottomSpace(width);
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return topInset + bottomInset
               + computeTopSpace(width)
               + container.prefHeight(width)
               + computeMinBottomSpace(width);
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return topInset + bottomInset
               + computeTopSpace(width)
               + container.maxHeight(width)
               + computeMaxBottomSpace(width);
    }

    private void down() {
        var destinationY = ((container.getHeight() / 2));

        animation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateYProperty(), 0
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateYProperty(), destinationY
                )),
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateXProperty(), label.getTranslateX()
                )),
                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateXProperty(), 0
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.scaleXProperty(), labelScale
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.scaleXProperty(), 1
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.scaleYProperty(), labelScale
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.scaleYProperty(), 1
                ))
        );

        animation.setOnFinished(e -> {
            top = false;
            label.setTranslateY(0);
        });
        animation.play();
    }

    private void up() {
        double displacementX = (label.getBoundsInLocal().getWidth() * (1.0 - labelScale)) / 2.0;

        var destinationX = getSkinnable().getIcon() != null ?
                -(getSkinnable().getIcon().getBoundsInLocal().getWidth() + container.getSpacing() + displacementX)
                : -(displacementX);

        animation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateYProperty(), 0
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateYProperty(), -((container.getHeight() / 2))
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.translateXProperty(), 0
                )),
                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.translateXProperty(), destinationX
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.scaleXProperty(), 1
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.scaleXProperty(), labelScale
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        label.scaleYProperty(), 1
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        label.scaleYProperty(), labelScale
                ))
        );
        animation.setOnFinished(e -> {
            label.setTranslateY(0);
            top = true;
        });
        animation.play();
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x,y,w,h);
        boolean hasSuggested = getChildren().contains(suggestedText);
        boolean hasCount = getChildren().contains(countText);

        double topSpace = computeTopSpace(w);
        double containerH = snapSizeY(container.prefHeight(w));

        // Maintain container height without shrinking when bottom elements appear
        layoutInArea(container, x, y + topSpace, w, containerH, -1, HPos.LEFT, VPos.CENTER);

        // Position the floating / static label
        double labelX = x + container.getInsets().getLeft();
        if (getSkinnable().getIcon() != null && getSkinnable().isAnimate()) {
            labelX += getSkinnable().getIcon().prefWidth(-1) + container.getSpacing();
        }

        if (getSkinnable().isAnimate()) {
            if (!top) {
                layoutInArea(label, labelX, y + topSpace, w, containerH, -1, HPos.LEFT, VPos.CENTER);
            } else {
                layoutInArea(label, labelX, y, w, topSpace * 2, -1, HPos.LEFT, VPos.TOP);
            }
        } else {
            layoutInArea(label, labelX, y, w, topSpace, -1, HPos.LEFT, VPos.TOP);
        }

        // Layout bottom elements with full bounds aligning to bottom
        if (hasSuggested) {
            layoutInArea(suggestedText, x, y, w, h, -1, HPos.LEFT, VPos.BOTTOM);
        }
        if (hasCount) {
            layoutInArea(countText, x, y, w, h, -1, HPos.RIGHT, VPos.BOTTOM);
        }
    }

    public HBox getContainer() {
        return container;
    }

    public TextField getEditor() {
        return editor;
    }
}
