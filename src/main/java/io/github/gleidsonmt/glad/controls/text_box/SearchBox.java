package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 11/09/2026
 */
public class SearchBox extends TextBox {

    public SearchBox(String text) {
        getStyleClass().add("search-box");
        setIcon(new SVGIcon(Icon.SEARCH));
        setActionable(true);
        setLabel("Search...");
        setAnimate(true);
//        getEditor().setPromptText(text);
//        setSuggested("Find you way bitch");
        setMax(50);
    }
}
