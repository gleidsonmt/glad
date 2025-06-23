package io.github.gleidsonmt.glad.theme;

import io.github.gleidsonmt.glad.Resources;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/01/2025
 */
public class ThemeProvider {

    @ApiStatus.Experimental
    public static void install(Parent parent, Font... css) {
        Arrays.stream(css).forEach(el -> parent.getStylesheets().add(Resources.getFont(el.getUrl())));
    }

    public static void install(Scene scene, Font... css) {
        Arrays.stream(css).forEach(el -> scene.getStylesheets().add(Resources.getFont(el.getUrl())));
    }

    @ApiStatus.Experimental
    public static void install(Parent parent, Css... css) {
        Arrays.stream(css).forEach(el -> parent.getStylesheets().add(Resources.getCss(el.getUrl())));
    }

    public static void install(Scene scene, Css... css) {
        Arrays.stream(css).forEach(el -> {
            if (el.toString().toLowerCase().contains("default")) {
                String[] list = el.getUrl().split(" ");
                for (String cs : list) {
                    scene.getStylesheets().add(Resources.getCss(cs));
                }
            } else {
                scene.getStylesheets().add(Resources.getCss(el.getUrl()));
            }
        });
    }

    @ApiStatus.Experimental
    @Deprecated(forRemoval = true)
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
