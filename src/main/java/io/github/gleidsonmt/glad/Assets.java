package io.github.gleidsonmt.glad;

import java.io.InputStream;
import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/02/2025
 */
public class Assets {

    public static String getCss(String name) {
        return Objects.requireNonNull(GladResources.class.getResource("css/" + name)).toExternalForm();
    }

    public static String getImage(String name) {
        return Objects.requireNonNull(GladResources.class.getResource("img/" + name)).toExternalForm();
    }

    public static InputStream getResourceAsStream(String name) {
        return Objects.requireNonNull(GladResources.class.getResourceAsStream("texts/" + name));
    }
}
