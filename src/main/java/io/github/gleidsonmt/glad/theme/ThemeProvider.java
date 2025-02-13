package io.github.gleidsonmt.glad.theme;

import io.github.gleidsonmt.glad.GladResources;
import javafx.scene.Parent;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/01/2025
 */
public class ThemeProvider {

//    // Testing
//    public enum Theme {
//        WHITE, DARK
//    }
//
//    // Testing
//    public static void set(Parent node, Theme theme) {
////        node.getStylesheets().addAll(this.c)
//    }

    public static void install(Parent parent, Font... css) {
        Arrays.stream(css).forEach(el -> {
            parent.getStylesheets().add(Objects.requireNonNull(GladResources.class.getResource("fonts/" + el.getUrl())).toExternalForm());
        });
    }

    public static void install(Parent parent, Css... css) {
        Arrays.stream(css).forEach(el -> parent.getStylesheets().add(Objects.requireNonNull(GladResources.class.getResource("css/" + el.getUrl())).toExternalForm()));
    }

    public static void install(Parent parent) {
        ThemeProvider.install(parent,
                Css.COLORS,
                Css.TYPOGRAPHIC,
                Css.SHAPES,
                Css.PROPERTIES,
                Css.BOOTSTRAP,

                Css.IMMERSIVE_SCROLL,

                Css.TAB_PANE,

                Css.PROGRESS_BAR,
                Css.HYPERLINK,
                Css.COMBO_BOX,
                Css.PROGRESS_BAR
        );
    }

}
