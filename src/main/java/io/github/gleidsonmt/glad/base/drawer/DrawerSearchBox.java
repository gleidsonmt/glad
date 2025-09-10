package io.github.gleidsonmt.glad.base.drawer;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/09/2025
 */
public class DrawerSearchBox extends TextBox {
    public DrawerSearchBox() {
        super(Icon.SEARCH, "", true);
        this.setPromptText("Search");
        this.setMinHeight(40);

    }
}
