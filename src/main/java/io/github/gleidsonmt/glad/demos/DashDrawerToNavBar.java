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
public class DashDrawerToNavBar extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Drawer drawer = new Drawer();

        Container container = new Container();

        container.setCenter(new StackPane(new Label("Welcome, Resize this stage.")));

        Root root = new Root(container);

        root.sizeProperty().addListener((observableValue, size, newValue) -> {
            System.out.println("newValue = " + newValue);
            if (newValue == Size.PHONE) {
                drawer.phoneLayout();
                container.getChildren().remove(drawer);
                container.setTop(drawer);
            } else {
//                container.setLeft(null);
                container.getChildren().remove(drawer);
                container.setLeft(drawer);

                drawer.tabletLayout();
            }
        });

//
        stage.setScene(new Scene(root, 800,600));
        stage.show();
    }

    private Node createDrawer() {
        GridPane drawer = new GridPane();

        return drawer;
    }

    private static class Drawer extends GridPane {
        public Drawer() {
            setStyle("-fx-background-color: white");
            setMinWidth(250);
            setMinHeight(100);
            setHgap(10);
            setVgap(10);
            getChildren().setAll(
                    new Label("Option 01"),
                    new Label("Option 02"),
                    new Label("Option 03")
            );
        }

        int col = 0;
        public void phoneLayout() {
            setAlignment(Pos.CENTER);
            col = 0;
            getChildren().forEach(el -> {
                GridPane.setConstraints(el, col++, 0);
            });
        }

        public void tabletLayout() {
            setAlignment(Pos.CENTER);
            col = 0;
            getChildren().forEach(el -> {
                GridPane.setConstraints(el, 0, col++);
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


