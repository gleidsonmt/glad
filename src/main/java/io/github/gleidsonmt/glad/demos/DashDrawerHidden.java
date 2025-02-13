package io.github.gleidsonmt.glad.demos;

import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.Size;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        Button hamb = new Button("→__→");

        container.setLeft(drawer);
        container.setCenter(new StackPane(new Label("Welcome, Resize this stage.")));

        Root root = new Root(container);
        root.setBreakpoint(800);

        hamb.setOnAction(e -> {
            root.behavior().openDrawer();
        });

        root.sizeProperty().addListener((observableValue, size, newValue) -> {
            System.out.println("newValue = " + newValue);
            if (newValue == Size.PHONE) {
                container.setTop(hamb);
            } else {
                container.setTop(null);
            }
        });


        stage.setScene(new Scene(root, 800,600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}