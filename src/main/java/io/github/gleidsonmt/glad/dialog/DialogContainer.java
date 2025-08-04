package io.github.gleidsonmt.glad.dialog;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class DialogContainer extends StackPane {



    public DialogContainer(Node... node) {
        if(node != null) this.getChildren().addAll(node);
        this.getStyleClass().addAll("bg-white","radius-5", "depth-2", "padding-10");

    }

}
