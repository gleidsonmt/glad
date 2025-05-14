package io.github.gleidsonmt.glad.demos;

import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/02/2025
 */
public class DashDrawerHidden extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        VBox drawer = new VBox(new Label("Drawer"));
        drawer.setAlignment(Pos.CENTER);
        drawer.setMinWidth(250);
        drawer.setStyle("-fx-background-color: white");

        Layout container = new Layout();

        Button hamb = new Button();
        hamb.setGraphic(new SVGIcon(Icon.MENU));

        container.setLeft(drawer);
        container.updateView(new StackPane(new Label("Welcome, Resize this stage.")));

        Root root = new Root(container);

        HBox nav = new HBox(hamb);
        nav.setAlignment(Pos.CENTER_RIGHT);

        hamb.setOnMouseClicked(e -> {
//            root.behavior().openDrawer();
            System.out.println("what");
            VBox box = new VBox(new Label("Welcome"));
            box.getStyleClass().addAll("bg-danger");
            box.setStyle("-fx-background-color: red;");
//            box.setMinSize(300, 300);
            box.setPrefSize(200, 100);
            root.flow().openByCursor(box, e, 0,40);
        });

        container.setTop(nav);
//        new Sizer<>(root, Break.values()) {
//            @Override
//            public void change(Break aBreak) {
//                if (aBreak == Break.SM) {
//                    container.setTop(nav);
//                    container.setLeft(null);
//                } else {
//                    container.setLeft(drawer);
//                    container.setTop(null);
//                    root.wrapper().close();
//                }
//            }
//        };

        stage.setScene(new Scene(root, 800, 400));
        stage.show();
        ScenicView.show(stage.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}