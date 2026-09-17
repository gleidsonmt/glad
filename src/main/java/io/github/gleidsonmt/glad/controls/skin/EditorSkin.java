

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.text_box.EditorOld;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  20/09/2022
 */
@Deprecated(forRemoval = true)
public class EditorSkin extends TextFieldSkin {

    private final BooleanProperty showPassword;

    public EditorSkin(TextField control) {
        super(control);

        this.showPassword = new SimpleBooleanProperty(false);

        // Force the skin to refresh the text representation when the boolean toggles
        showPassword.addListener((obs, oldVal, newVal) -> {
            // Trick JavaFX into thinking the text changed so it invokes maskText()
            String currentText = control.getText();
//                passwordField.setText(null);
            control.setText(currentText);
//
// Move caret back to the end of the text
            control.end();

        });


    }

    @Override
    protected String maskText(String txt) {
        if (showPassword == null) {
            return super.maskText(txt);
        }
        if (showPassword.get()) {
            return txt;
        }
        return super.maskText(txt);
    }

    public boolean isShowPassword() {
        return showPassword.get();
    }

    public BooleanProperty showPasswordProperty() {
        return showPassword;
    }
}