

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.ComponentSkin;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  10/09/2022
 */
@Deprecated(forRemoval = true)
public class TextBoxBaseSkin extends SkinBase<TextBoxBase> implements ComponentSkin<TextBoxBase> {

    //Fixed
    protected final TextBoxBase control;

    protected TextBoxBaseSkin(TextBoxBase _control) {
        super(_control);
        this.control = _control;

        control.editorProperty().addListener((_, oldVal, newValue) -> {
            if (newValue != null) {
                if (!getChildren().contains(newValue)) {
                    getChildren().add(newValue);
                    bind(newValue);
//                    newValue.setFakeFocus(true);
                }
                if (oldVal != null) {
                    unbind(oldVal);
                }
            }
        });

        if (control.getEditor() != null) {
            if (!getChildren().contains(control.getEditor()))
                getChildren().add(control.getEditor());
            bind(control.getEditor());
        }

        if (control.getRightNode() != null) {

            control.getRightNode().setManaged(false);
            getChildren().add(_control.getRightNode());
        }

        control.leftNodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getChildren().add(1, newValue);
//                newValue.setManaged(false);
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

//        control.getEditor().setStyle("-fx-background-color: green; -fx-min-height: 100px; -fx-pref-height: 100px; -fx-max-height: 100px;");

    }

    private void bind(TextField editor) {
        editor.minHeightProperty().bind(control.minHeightProperty());
        editor.prefHeightProperty().bind(control.prefHeightProperty());
        editor.maxHeightProperty().bind(control.maxHeightProperty());
    }

    private void unbind(TextField editor) {
        editor.minHeightProperty().unbind();
        editor.prefHeightProperty().unbind();
        editor.maxHeightProperty().unbind();
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return control.getEditor().prefHeight(width) + topInset + bottomInset + rightInset + leftInset;
    }

//    @Override
//    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return control.maxHeight(width) + topInset + bottomInset + rightInset + leftInset;
//    }

    //    @Override
//    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return
//                super.computePrefHeight(control.getEditor().getHeight(), topInset, rightInset, bottomInset, leftInset)
//                +
//                control.getEditor().prefHeight(width) + topInset + bottomInset
//                ;
//
//    }
//
//    @Override
//    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return
//                super.computePrefHeight(width, topInset, rightInset, bottomInset, leftInset)
//                +
//                control.getEditor().minHeight(width) + topInset + bottomInset
//                ;
//    }


    private double getSpacing() {
        return 5;
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        var leftSpace = control.getLeftNode() != null ? control.getLeftNode().getBoundsInLocal().getWidth() : 0;

        var editorX = x + leftSpace;
        var editorWidth = w - leftSpace;

        layoutInArea(control.getEditor(), editorX, y, editorWidth , h, -1, HPos.LEFT, VPos.CENTER);

        if (control.getLeftNode() != null) {
            layoutInArea(control.getLeftNode(), x, y, w , h, -1, HPos.LEFT, VPos.CENTER);
        }

//        double edX;
//        double edW;
//
//        if (getChildren().contains(control.getRightNode()) && getChildren().contains(control.getLeftNode())) {
//
//            edX = x + control.getLeftNode().getBoundsInLocal().getWidth();
//            edW = w - (control.getRightNode().getBoundsInLocal().getWidth() +
//                       control.getLeftNode().getBoundsInLocal().getWidth());
//
//        } else if (getChildren().contains(control.getLeftNode())) {
//
//            edX = x + control.getLeftNode().getBoundsInLocal().getWidth();
//            edW = w - control.getLeftNode().getBoundsInLocal().getWidth();
//
//        } else if (getChildren().contains(control.getRightNode())) {
//
//            edX = x;
//            edW = w - control.getRightNode().getBoundsInLocal().getWidth();
//
//        } else {
//            edX = x;
//            edW = w;
//        }
//
//        edX = control.getLeftNode() == null ? edX - control.getSpacing() : edX + getSkinnable().getSpacing();
//        edW = control.getLeftNode() != null ? edW - control.getSpacing() :  edW + getSkinnable().getSpacing();
//
//        if (getChildren().contains(control.getEditor())) {
//            layoutInArea(control.getEditor(), edX - 2, y, edW , h, -1, HPos.LEFT, VPos.CENTER);
//        }
//
//        if (getChildren().contains(control.getLeftNode()))
//            layoutInArea(control.getLeftNode(), x, y, w, h, -1, HPos.LEFT, VPos.CENTER);
//
//        if (getChildren().contains(control.getRightNode())) {
//            layoutInArea(control.getRightNode(), x, y, w, h, -1, HPos.RIGHT, VPos.CENTER);
//        }

//        if (getChildren().contains(helperLabel)) {
//            layoutInArea(helperLabel, x, y, w, h, -1, HPos.LEFT, VPos.BOTTOM);
//            helperLabel.relocate(x, h + 5);
//            helperLabel.requestLayout();
//        }

//        if (control.getEditor() instanceof FloatEditorOld floatEditor) {
//            floatEditor.resize(edW, control.getHeight());
//        } else {
//            if (control.getEditor() != null)
//                control.getEditor().resize(edW, control.getHeight());
//        }

//        layoutInArea(control.getEditor(), edX, y, w - edX,h, -1, HPos.LEFT, VPos.CENTER);

    }

    @Override
    public void bind(TextBoxBase _control) {

    }

    @Override
    public void setInitialState(TextBoxBase _control) {

    }
}
