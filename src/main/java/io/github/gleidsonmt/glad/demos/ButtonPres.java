package io.github.gleidsonmt.glad.demos;

import io.github.gleidsonmt.blockcode.BlockCode;
import io.github.gleidsonmt.blockcode.BlockCodeView;
import io.github.gleidsonmt.blockcode.Theme;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/02/2025
 */
public class ButtonPres extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BlockCodeView blockCodeView = new BlockCodeView();
        blockCodeView.setJavaCode("Button btn = new Button();");
        VBox body = new VBox(blockCodeView, new Button("Button"));
        body.setAlignment(Pos.CENTER);

        Root root = new Root(new Container(body));
        stage.setScene(new Scene(root, 800, 600));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
