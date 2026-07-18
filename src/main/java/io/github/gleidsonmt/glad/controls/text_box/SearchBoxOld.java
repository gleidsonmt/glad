

package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.icon.Icon;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  02/10/2025
 */
public class SearchBoxOld extends TextBoxOld {

    public SearchBoxOld() {
        super(Icon.SEARCH, "", true);
        this.setPromptText("Search");
        this.setMinHeight(40);
    }
}
