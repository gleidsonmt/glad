package io.github.gleidsonmt.glad.base;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2025
 */
public class SnackBar extends GridPane  {

    private Node graphic;
    private final TextFlow textFlow;
    private final Text text;

    public SnackBar(String message) {
        this.text = new Text(message);
        this.textFlow = createTextFlow();
        getStyleClass().addAll("min-h-50 bg-white depth-1 align-center rounded border-1 border-light-gray".split(" "));
        setPadding(new Insets(5, 20, 5, 20));
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        add(textFlow, 1, 0);

    }

    private TextFlow createTextFlow() {
        TextFlow textFlow = new TextFlow(text);
        text.getStyleClass().addAll("bold text-16 ".split(" "));
        textFlow.getStyleClass().addAll("padding-10".split(" "));
        return textFlow;
    }

    public void setGraphic(Node graphic) {
        this.graphic = graphic;
    }

    public Node getGraphic() {
        return graphic;
    }


}
