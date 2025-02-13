package io.github.gleidsonmt.glad.base;

import javafx.beans.property.BooleanProperty;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/02/2025
 */
public interface Behavior {

    void openDrawer();

    void closeDrawer();

    void openAside();

    void closeAside();

    boolean isDrawerOpen();

}
