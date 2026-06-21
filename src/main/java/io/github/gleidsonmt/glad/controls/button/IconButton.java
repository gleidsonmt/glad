

package io.github.gleidsonmt.glad.controls.button;

import javafx.beans.DefaultProperty;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  25/09/2022
 */
@DefaultProperty("control")
public class IconButton extends Button {

    public IconButton() {
        this(null);
    }

    public IconButton(Node icon) {
        this(icon, false);
    }

    public IconButton(Node icon, boolean inside) {
        this(icon, inside, null);
    }

    public IconButton(Node icon, boolean inside, String[] classes) {
        setGraphic(icon);
        getStyleClass().addAll(inside ? "inside-button" : "icon-button", "w-40", "h-40");
//        getStyleClass().addAll(inside ? "inside-button" : "icon-button", "w-40", "h-40");
        if (classes != null) getStyleClass().addAll(classes);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    public void setIcon(Node icon) {
        setGraphic(icon);
    }

}