package io.github.gleidsonmt.glad.drawer;

import io.github.gleidsonmt.glad.base.Module;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2025
 */
public class DrawerMenu extends TitledPane {

    public DrawerMenu(Module module) {

        VBox content = new VBox();
        content.getStyleClass().add("container");
        setText(module.getName());
        setContent(content);
        setUserData(module);
//
        if (module.getGraphic() != null) {
            this.setGraphic(module.getGraphic());
        }
        this.setExpanded(false);
//
        this.setContentDisplay(ContentDisplay.RIGHT);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
//
        this.getStyleClass().add("drawer-menu");
        content.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

//        if (!module.isAnimated() && !first) {
//        System.out.println("module = " + module.isAnimated());
//        if (!module.isAnimated()) {
//            ToggleButton b = new ToggleButton(module.getName());
//            b.setUserData(module);
//            b.getStyleClass().add("drawer-item");
//            b.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
//            b.setAlignment(Pos.CENTER_LEFT);
//            b.setPrefWidth(230);
//            group.getToggles().add(b);
//            this.getStyleClass().add("module-item");
//
////            this.setGraphic(b);
//            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//            this.setCollapsible(false);
//            this.setAnimated(false);

//            content.getChildren().add(b);

//            b.setOnMouseClicked(e ->
//            {
//                if (!(e.getTarget() instanceof VBox)) {
//                    currentModule.set(module);
//                }
//            });
//        }
    }
}
