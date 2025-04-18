package io.github.gleidsonmt.glad.theme;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  31/01/2025
 */
public enum Css {

    DEFAULT("colors.css typographic.css properties.css shapes.css immersive_scroll.css"),

    TOGGLE_SWITCH("toggle-switch.css"),
    TREE_VIEW("tree-view.css"),
    LIST_VIEW("list-view.css"),
    TOGGLE_BUTTON("toggle-button.css"),
    BUTTON("button.css"),
    TABLE_VIEW("table-view.css"),
    HYPERLINK("hyperlink.css"),
    CONTEXT_MENU("context-menu.css"),
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


    PROGRESS_BAR("progress-bar.css"),
    TEXT_FIELD("text-field.css");

    private String url;

    Css(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
