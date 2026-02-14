package io.github.gleidsonmt.glad.drawer;

import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.Root;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
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