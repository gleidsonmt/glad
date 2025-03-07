package io.github.gleidsonmt.glad.demos;

import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.responsive.Break;
import io.github.gleidsonmt.glad.responsive.sizer.Sizer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        Container container = new Container();

        Button hamb = new Button();
        hamb.setGraphic(new SVGIcon(Icon.MENU));

        container.setLeft(drawer);
        container.updateView(new StackPane(new Label("Welcome, Resize this stage.")));

        Root root = new Root(container);

        hamb.setOnAction(e -> {
            root.behavior().openDrawer();
        });

        new Sizer<>(root, Break.values()) {
            @Override
            public void change(Break aBreak) {
                if (aBreak == Break.SM) {
                    container.setTop(hamb);
                    container.setLeft(null);
                } else {
                    container.setLeft(drawer);
                    container.setTop(null);
                    root.wrapper().close();
                }
            }
        };

        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}