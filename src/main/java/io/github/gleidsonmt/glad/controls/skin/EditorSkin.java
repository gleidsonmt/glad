

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.text_box.Editor;
import io.github.gleidsonmt.glad.controls.text_box.FloatEditor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  20/09/2022
 */
@Deprecated(forRemoval = true)
public class EditorSkin extends TextFieldSkin {

    public EditorSkin(Editor _control) {
        super(_control);
    }

    @Override
    protected String maskText(String txt) {
        if (getSkinnable() instanceof Editor editor) {
            if (editor.isMaskText()) {
                final char BULLET = '\u25cf';
                int n = txt.length();

                return String.valueOf(BULLET).repeat(n);
            } else {
                return editor.textProperty().getValueSafe();
            }
        } else return txt;
    }


//    @Override
//    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return getSkinnable().minHeight(width) + label.minHeight(width);
//    }
//
//    @Override
//    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return getSkinnable().prefHeight(width) + label.prefHeight(width);
//    }

//    @Override
//    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return getSkinnable().maxHeight(width) + label.maxHeight(width);
//    }

//    @Override
//    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return
//                super.computeMinHeight(width, topInset+30, rightInset, bottomInset, leftInset)
//                +
//                label.minHeight(width) + 20+150
//
//                ;
//    }






}