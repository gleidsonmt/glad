package io.github.gleidsonmt.glad.theme;

import org.jetbrains.annotations.Contract;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/02/2025
 */
public enum Font {

    INSTAGRAM("instagram.css"),
    POPPINS("poppins.css");

    private final String url;

    @Contract(pure = true)
    Font(String url) {
        this.url = url;
    }

    @Contract(pure = true)
    public String getUrl() {
        return url;
    }

}
