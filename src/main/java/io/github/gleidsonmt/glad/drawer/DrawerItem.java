

package io.github.gleidsonmt.glad.drawer;

import io.github.gleidsonmt.glad.base.drawer.Module;
import io.github.gleidsonmt.glad.base.Root;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  17/04/2025
 */
public class DrawerItem extends ToggleButton {

    public DrawerItem(Module item) {
        super(item.getName());
        setUserData(item);
        getStyleClass().add("drawer-item");
        setAlignment(Pos.CENTER_LEFT);
        setPrefWidth(Double.MAX_VALUE);

        addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> {
            Root root = (Root) getScene().getRoot();
            if (root.behavior().dialog().isShowing()) {
                root.behavior().dialog().hide();
            }
        });

        if (item.getGraphic() != null) {
            setGraphic(item.getGraphic());
        }

        setUserData(item);
    }
}