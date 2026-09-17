package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.skin.TextBoxSkinExample;
import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 18/07/2026
 */
public class PasswordBoxExample extends TextBoxExample {

    @Override
    protected Skin<?> createDefaultSkin() {
        return new TextBoxSkinExample(this, true);
    }
}
