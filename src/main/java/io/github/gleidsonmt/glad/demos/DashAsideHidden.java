package io.github.gleidsonmt.glad.demos;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/02/2025
 */
public class DashAsideHidden extends Application {

    @Override
    public void start(Stage stage) throws Exception {

//        VBox aside = new VBox(new Label("Aside"));
//        aside.setAlignment(Pos.CENTER);
//        aside.setMinWidth(250);
//        aside.setStyle("-fx-background-color: white");
//
//        Layout container = new Layout();
//        Button hamb = new Button("Open Aside");
//
//        container.updateView(new StackPane(new Label("Welcome, Resize this stage.")));
//
//        Root rootImpl = new Root(container);
//        ThemeProvider.install(rootImpl, Font.POPPINS);
////        container.setRight(aside);
//        hamb.setOnAction(e -> {
////            rootImpl.behavior().openAside();
//        });
//
//        container.addPoint(pt -> {
//            container.setRight(null);
//            container.setTop(hamb);
//        }, Break.SM);
//
//        container.addPoint(pt -> {
//            container.setTop(null);
//            container.setRight(aside);
//            rootImpl.wrapper().hide();
//        }, Break.values());
//
//        stage.setScene(new Scene(rootImpl, 800, 600));
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}