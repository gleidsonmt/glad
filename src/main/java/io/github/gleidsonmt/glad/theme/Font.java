

package io.github.gleidsonmt.glad.theme;

import org.jetbrains.annotations.Contract;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  01/02/2025
 */
public enum Font implements Neutral {

    INSTAGRAM("instagram.css"),
    POPPINS("poppins.css");

    private final String url;

    @Contract(pure = true)
    Font(String url) {
        this.url = url;
    }

    @Contract(pure = true)
    @Override
    public String getUrl() {
        return url;
    }

}
