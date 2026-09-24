package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 05/09/2026
 */
public class PasswordBox extends TextBox {

    public PasswordBox() {
        this(null, null, false, new PasswordField());
    }

    public PasswordBox(Icon icon) {
        this(icon, null, false);
    }

    public PasswordBox(String label) {
        this(Icon.NONE, label, false);
    }

    public PasswordBox(Icon icon, String label, boolean action) {
        super(icon, label, action);
    }

    public PasswordBox(Node icon, String label, boolean action) {
        this(icon, label, action, new PasswordField());
    }

    protected PasswordBox(Node icon, String label, boolean action, TextField editor) {
        super(icon, label, action, editor);
    }


    @Override
    protected Skin<?> createDefaultSkin() {
//        var passField = new PasswordField();
//        passField.setSkin(new EditorSkin(passField));
        return new TextBoxSkin(this);
    }
}
