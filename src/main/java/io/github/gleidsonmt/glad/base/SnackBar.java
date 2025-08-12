package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.animations.Anchor;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2025
 */
public class SnackBar extends GridPane implements Snack {

    private Root root;

    private Node graphic;
    private final TextFlow textFlow;
    private final Text text = new Text();
    private Node actions;
    
    public SnackBar(Root root) {
        this.root = root;
        this.textFlow = createTextFlow();
        getStyleClass().addAll("min-size-50 bg-white raised align-center rounded".split(" "));
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

    private TextFlow createTextFlow() {
        TextFlow textFlow = new TextFlow(text);
        text.getStyleClass().addAll("bold text-16 ".split(" "));
        textFlow.getStyleClass().addAll("font-instagram-headline padding-10".split(" "));
        return textFlow;
    }

    public void setGraphic(Node graphic) {
        this.graphic = graphic;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setActions(Node actions) {
        this.actions = actions;
    }

    public Node getGraphic() {
        return graphic;
    }

    public Node getActions() {
        return actions;
    }

    @Override
    public void show() {

        add(textFlow, 1, 0);

        TimerTask hideSnack = new TimerTask() {
            @Override
            public void run() {
              hide();
            }
        };

        Timer timer = new Timer();
        timer.schedule(hideSnack, 3000);

        root.flow()
                .pos(Pos.BOTTOM_CENTER)
                .content(this)
                .anchor(Anchor.BOTTOM)
                .insets(new Insets(20))
                .show();
    }

    @Override
    public void hide() {
        Platform.runLater(() -> root.flow()
                .remove(this));
    }
}
