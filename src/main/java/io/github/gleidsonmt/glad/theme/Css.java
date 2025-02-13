package io.github.gleidsonmt.glad.theme;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  31/01/2025
 */
public enum Css {

    HYPERLINK("hyperlink.css"),
    CONTEXT_MENU("context-menu.css"),
    COMBO_BOX("combo-box.css"),
    COLORS("colors.css"),
    TAB_PANE("tab-pane.css"),
    PROPERTIES("properties.css"),
    BOOTSTRAP("bootstrap.css"),
    SHAPES("shapes.css"),
    IMMERSIVE_SCROLL("immersive_scroll.css"),
    TYPOGRAPHIC("typographic.css"),


    PROGRESS_BAR("progress-bar.css")
    ;

    private String url;

    Css(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
