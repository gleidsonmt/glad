

package io.github.gleidsonmt.glad.base.drawer;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Box module is a contente for drawer menus.
 * With them they have a name of that module and a toggle button.
 * The drawer shows up a vbox with a text as name and toggle button representing the view.
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  31/03/2025
 */
public class BoxModule extends VBox {

    private final String name;

    public BoxModule(String name, ToggleButton toggleButton) {
        this.name = name;
        getStyleClass().add("box-module");
        Text title = new Text(name);

        getChildren().add(title);
        VBox.setMargin(title, new Insets(5));

        this.getChildren().addListener((ListChangeListener<Node>) change -> {
            if (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList()
                            .forEach(el ->
                                    VBox.setMargin(el, new Insets(0, 20, 0, 20)));
                }
            }
        });
        getChildren().add(toggleButton);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}