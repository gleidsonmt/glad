

package io.github.gleidsonmt.glad.drawer;

import io.github.gleidsonmt.glad.base.Module;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  10/09/2025
 */
public class DrawerSeparator extends VBox {

    public DrawerSeparator(Module item) {
        Label lbl = new Label(item.getName());
        lbl.setGraphic(item.getGraphic());
        lbl.getStyleClass().add("font-instagram-headline");
//            label.setStyle("-fx-font-family: \"Instagram Sans\"; -fx-font-size: 12px;");
//            label.setCache(true);
//            label.setCacheHint(CacheHint.QUALITY);
        getChildren().setAll(lbl, new Separator());
//            box.setPadding(new Insets(0, 5,0,5));
        VBox.setMargin(this, new Insets(10, 5, 0, 5));
        this.setSpacing(10);
    }

}
