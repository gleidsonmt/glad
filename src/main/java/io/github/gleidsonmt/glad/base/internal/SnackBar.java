

package io.github.gleidsonmt.glad.base.internal;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  12/08/2025
 */
public class SnackBar extends GridPane {

    private Node graphic;
    private final TextFlow textFlow;
    private final Text text;

    public SnackBar(String message) {
        this.text = new Text(message);
        this.textFlow = createTextFlow();
        setHgap(10);
        getStyleClass().addAll("min-h-50 bg-white depth-1 align-center rounded border-1 border-light-gray".split(" "));
        setPadding(new Insets(5, 20, 5, 20));
        textFlow.setTextAlignment(TextAlignment.CENTER);

        add(textFlow, 1, 0, 1, 1);
        GridPane.setHgrow(textFlow, Priority.ALWAYS);
        GridPane.setVgrow(textFlow, Priority.ALWAYS);

    }

    private TextFlow createTextFlow() {
        TextFlow textFlow = new TextFlow(text);
        text.getStyleClass().addAll("bold text-16 ".split(" "));
        textFlow.getStyleClass().addAll("padding-10".split(" "));
        return textFlow;
    }

    public void setGraphic(Node graphic) {
        add(graphic, 0, 0);
        GridPane.setHgrow(graphic, Priority.ALWAYS);

    }

    public Node getGraphic() {
        return graphic;
    }


}
