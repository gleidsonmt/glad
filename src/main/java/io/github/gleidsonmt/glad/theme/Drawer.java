package io.github.gleidsonmt.glad.theme;

import org.jetbrains.annotations.Contract;

import javax.xml.crypto.Data;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2025
 */
public enum Drawer implements Neutral {

    DEFAULT("drawer.css"),
    DRAWER1("drawer-plus.css");

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
