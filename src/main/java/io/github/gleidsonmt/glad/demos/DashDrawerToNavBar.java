package io.github.gleidsonmt.glad.demos;

import io.github.gleidsonmt.glad.base.RootImpl;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/02/2025
 */
public class DashDrawerToNavBar extends Application {

    @Override
    public void start(Stage stage) {
//        Drawer drawer = new Drawer();
//        Layout container = new Layout();
//
//        container.setCenter(new StackPane(new Label("Welcome, Resize this stage.")));
//
//        RootImpl rootImpl = new RootImpl(container);
//
//        new Sizer<>(rootImpl, Break.values()) {
//            @Override
//            public void change(Break aBreak) {
//                if (aBreak == Break.SM) {
//                    drawer.phoneLayout();
//                    container.getChildren().remove(drawer);
//                    container.setTop(drawer);
//                } else {
//                    container.getChildren().remove(drawer);
//                    container.setLeft(drawer);
//
//                    drawer.tabletLayout();
//                }
//            }
//        };
//
//        stage.setScene(new Scene(rootImpl, 800, 600));
//        stage.show();
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


