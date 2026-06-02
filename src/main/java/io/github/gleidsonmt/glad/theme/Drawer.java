

package io.github.gleidsonmt.glad.theme;

import org.jetbrains.annotations.Contract;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  10/09/2025
 */
public enum Drawer implements Neutral {

    DEFAULT("drawer.css");

    private final String url;

    @Contract(pure = true)
    Drawer(String url) {
        this.url = url;
    }

    @Contract(pure = true)
    @Override
    public String getUrl() {
        return url;
    }
}
