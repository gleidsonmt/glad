package io.github.gleidsonmt.glad.demos;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/02/2025
 */
public class DashDrawerHidden extends Application {

    @Override
    public void start(Stage stage) throws Exception {

//        VBox drawer = new VBox(new Label("Drawer"));
//        drawer.setAlignment(Pos.CENTER);
//        drawer.setMinWidth(250);
//        drawer.setStyle("-fx-background-color: white");
//
//        Layout container = new Layout();
//
//        Button hamb = new Button();
//        hamb.setGraphic(new SVGIcon(Icon.MENU));
//
//        container.setLeft(drawer);
//        container.updateView(new StackPane(new Label("Welcome, Resize this stage.")));
//
//        Root rootImpl = new Root(container);
//
//        HBox nav = new HBox(hamb);
//        nav.setAlignment(Pos.CENTER_RIGHT);
//
//        hamb.setOnMouseClicked(e -> {
////            rootImpl.behavior().openDrawer();
//            System.out.println("what");
//            VBox box = new VBox(new Label("Welcome"));
//            box.getStyleClass().addAll("bg-danger");
//            box.setStyle("-fx-background-color: red;");
////            box.setMinSize(300, 300);
//            box.setPrefSize(200, 100);
//            rootImpl.flow().openByCursor(box, e, 0,40);
//        });
//
//        container.setTop(nav);
//        new Sizer<>(rootImpl, Break.values()) {
//            @Override
//            public void change(Break aBreak) {
//                if (aBreak == Break.SM) {
//                    container.setTop(nav);
//                    container.setLeft(null);
//                } else {
//                    container.setLeft(drawer);
//                    container.setTop(null);
//                    rootImpl.wrapper().close();
//                }
//            }
//        };

//        stage.setScene(new Scene(rootImpl, 800, 400));
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}