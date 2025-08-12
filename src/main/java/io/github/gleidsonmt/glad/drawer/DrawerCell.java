package io.github.gleidsonmt.glad.drawer;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/04/2025
 */
public class DrawerCell extends GridPane {

    private final Pane pane;

    public DrawerCell(DrawerItem item) {
        Text text = new Text(item.getName());
        pane = new Pane();
        pane.setMinWidth(5);

        pane.setStyle("-fx-background-color: -fx-background; -fx-background-radius: 0px 5px 5px 0px;");
        setHgrow(text, Priority.ALWAYS);
        setAlignment(Pos.CENTER_LEFT);
        setVgrow(pane, Priority.ALWAYS);
        add(pane, 0, 0, 1, REMAINING);
        if (item.getGraphic() != null) add(item.getGraphic(), 1, 0);
        add(text, item.getGraphic() != null ? 2 : 1, 0);
        if (item.getIcon() != null) add(item.getIcon(), 3, 0);

        setHgap(10);
        getStyleClass().addAll("rounded", "border-2", "border-transparent", "padding-5");

        hoverProperty().addListener((observableValue, aBoolean, newVal) -> {
            if (newVal) {
//                getStyleClass().addAll("border-2", "border-light-gray-2", "radius-2", "cursor-hand");
                getStyleClass().addAll( "border-light-gray-2");
            } else {
                getStyleClass().removeAll( "border-light-gray-2");
//                getStyleClass().removeAll("border-2", "border-red-500", "radius-2", "cursor-hand");
            }
        });
    }

    public Pane getPane() {
        return pane;
    }
}
