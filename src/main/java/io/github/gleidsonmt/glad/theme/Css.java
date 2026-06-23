

package io.github.gleidsonmt.glad.theme;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  31/01/2025
 */
public enum Css implements Neutral {

    //    DEFAULT("colors.css typographic.css properties.css shapes.css immersive_scroll.css"),
    DEFAULT("colors.css typographic.css properties.css shapes.css immersive_scroll.css"),

    TOGGLE_SWITCH("toggle-switch.css"),
    LABEL("label.css"),
    CHOICE_BOX("choice-box.css"),
    TREE_VIEW("tree-view.css"),
    LIST_VIEW("list-view.css"),
    TOGGLE_BUTTON("toggle-button.css"),
    BUTTON("button.css"),
    TABLE_VIEW("table-view.css"),
    HYPERLINK("hyperlink.css"),
    CONTEXT_MENU("context-menu.css"),
    MENU_BUTTON("menu-button.css"),
    SPLIT_MENU_BUTTON("split-menu-button.css"),
    COMBO_BOX("combo-box.css"),
    CHECK_BOX("check-box.css"),
    RADIO_BUTTON("radio-button.css"),
    TEXT_BOX("text-box.css"),
    COLORS("colors.css"),
    TAB_PANE("tab-pane.css"),
    PROPERTIES("properties.css"),
    BOOTSTRAP("bootstrap.css"),
    SHAPES("shapes.css"),
    IMMERSIVE_SCROLL("immersive_scroll.css"),
    TYPOGRAPHIC("typographic.css"),

    SCROLLBAR("scroll-bar.css"),

    PROGRESS_BAR("progress-bar.css"),
    TEXT_FIELD("text-field.css"),
    SPINNER("spinner.css"),
    DATE_PICKER("date_picker.css"),
    TITLED_PANE("titled-pane.css"),

    BADGE("badge.css"),
    SLIDER("slider.css"),
    THEME_DEFAULT("theme.css"),

    ALL("ALL")
    ;

    private final String url;

    Css(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
