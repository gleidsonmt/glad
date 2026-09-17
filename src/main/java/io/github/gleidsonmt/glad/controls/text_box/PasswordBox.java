package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 05/09/2026
 */
public class PasswordBox extends TextBox {

    public PasswordBox() {
    }

    public PasswordBox(Icon icon, String label, boolean action) {
        super(icon, label, action);
    }

    public PasswordBox(Icon icon) {
        super(icon, null, false);
    }

    public PasswordBox(String label) {
        super(Icon.NONE, label, false);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
//        var passField = new PasswordField();
//        passField.setSkin(new EditorSkin(passField));
        return new TextBoxSkin(this, new PasswordField());
    }
}
