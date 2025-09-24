package io.github.gleidsonmt.glad.theme;

import io.github.gleidsonmt.glad.Resources;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;

/**
 * This class provides all the css to the scene.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/01/2025
 */
public class ThemeProvider {
    /**
     * Install all the css.
     * @param scene The scene to install the css.
     */
    public static void install(Scene scene) {
        install(scene, Css.ALL);
    }

    /**
     * Install the css in the scene.
     * @param scene the scene to install the css.
     * @param css the css to install.
     */
    @ApiStatus.Experimental
    public static void install(Scene scene, Neutral... css) {
        Arrays.stream(css).forEach(el -> {
            switch (el) {
                case Font font -> install(scene, font);
                case Css cs -> install(scene, cs);
                case Drawer drawer -> install(scene, drawer);
                default -> throw new IllegalArgumentException("Invalid type of css: " + el.getClass().getName());
            }
        });
    }

    public static void install(Scene scene, Drawer... css) {
        Arrays.stream(css).forEach(el -> scene.getStylesheets().add(Resources.getDrawer(el.getUrl())));
    }

    public static void install(Scene scene, Font... css) {
        Arrays.stream(css).forEach(el -> scene.getStylesheets().add(Resources.getFont(el.getUrl())));
    }

    /**
     * Install the css in the scene.
     * @param scene the scene to install the css.
     * @param css the css to install.
     */
    public static void install(Scene scene, Css... css) {
        if (Arrays.stream(css).anyMatch(el -> el.toString().toLowerCase().contains("all"))) {
            for (Css el : Css.values()) {
                if (el != Css.DEFAULT && el != Css.ALL) {
                    scene.getStylesheets().add(Resources.getCss(el.getUrl()));
                }
            }
            install(scene, Font.POPPINS);
            install(scene, Drawer.DEFAULT);
            return;
        }
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
}