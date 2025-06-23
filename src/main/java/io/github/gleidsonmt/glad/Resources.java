package io.github.gleidsonmt.glad;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/02/2025
 */
public interface Resources {

    static String getCss(String name) {
        return Objects.requireNonNull(Resources.class.getResource("css/" + name)).toExternalForm();
    }

    static String getFont(String name) {
        return Objects.requireNonNull(Resources.class.getResource("fonts/" + name)).toExternalForm();
    }

    static String getAgent(String name) {
        return Objects.requireNonNull(Resources.class.getResource("agents/" + name)).toExternalForm();
    }

    static Image getImage(String name) {
        return new Image(Objects.requireNonNull(Resources.class.getResource("img/" + name)).toExternalForm());
    }

}
