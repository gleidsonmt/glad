

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.text_box.Editor;
import javafx.scene.Parent;
import javafx.scene.control.skin.TextFieldSkin;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  20/09/2022
 */
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
}