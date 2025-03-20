package io.github.gleidsonmt.glad.demos;

import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.responsive.Break;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/02/2025
 */
public class DashAsideHidden extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        VBox aside = new VBox(new Label("Aside"));
        aside.setAlignment(Pos.CENTER);
        aside.setMinWidth(250);
        aside.setStyle("-fx-background-color: white");

        Layout container = new Layout();
        Button hamb = new Button("Open Aside");

        container.updateView(new StackPane(new Label("Welcome, Resize this stage.")));

        Root root = new Root(container);
        ThemeProvider.install(root, Font.POPPINS);
//        container.setRight(aside);
        hamb.setOnAction(e -> {
            root.behavior().openAside();
        });

        container.addPoint(pt -> {
            container.setRight(null);
            container.setTop(hamb);
        }, Break.SM);

        container.addPoint(pt -> {
            container.setTop(null);
            container.setRight(aside);
            root.wrapper().close();
        }, Break.values());

        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}