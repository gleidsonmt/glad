package io.github.gleidsonmt.glad;

import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.View;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class Main extends View {

    public Main() {
        super("Main");
        Button btn = new Button("Click on me!");
        VBox body = new VBox(btn, new Label("Hello Dash!"));
        body.getStyleClass().addAll("bg-white padding-10 align-center".split(" "));
        this.content = new StackPane();
        ((StackPane) this.content).getChildren().setAll(body);
    }


    @Override
    public void onEnter(Layout layout) {

    }

    @Override
    public void onExit(Layout layout) {

    }
}
