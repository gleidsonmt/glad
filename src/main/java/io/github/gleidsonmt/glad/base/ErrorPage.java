

package io.github.gleidsonmt.glad.base;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  02/07/2023
 */
public class ErrorPage extends ScrollPane {

    private final VBox body = new VBox();
    private final VBox boxTitle = new VBox();
    private final VBox boxInfo = new VBox();

    private final Text errorTitle = new Text();
    private final Text title = new Text();
    private final Text legend = new Text();
    private final Text info = new Text();

    private final TextFlow titleFlow = new TextFlow(title);
    //    private final Hyperlink see = new Hyperlink("See Routes");
    private final TextFlow infoFlow = new TextFlow(info);

    public ErrorPage(String name) {
        VBox.setVgrow(this, Priority.ALWAYS);
        errorTitle.setText("404");
        title.setText("The module was not found.");
        legend.setText("Sorry, system couldn't find the view.");
        info.setText("View/ModuleView \'" + name + "\' doesn't exist.");
        configLayout();
    }

    private void configLayout() {

        errorTitle.getStyleClass().addAll("bolder", "text-48");
        errorTitle.setStyle("-fx-fill: -fx-accent;");
        title.getStyleClass().addAll("text-bold", "h1");
        title.getStyleClass().addAll("-fx-fill: -text-color;");
        legend.getStyleClass().addAll("-fx-fill: -text-color;");
        info.getStyleClass().addAll("h6");
        info.setStyle("-fx-fill: -text-color;");
        titleFlow.setTextAlignment(TextAlignment.CENTER);
        infoFlow.setTextAlignment(TextAlignment.CENTER);
        boxInfo.getChildren().addAll(infoFlow);

        body.getChildren().setAll(boxTitle, boxInfo);
        this.setContent(body);
        boxTitle.getChildren().setAll(errorTitle, titleFlow);

        body.setAlignment(Pos.CENTER);
        boxTitle.setAlignment(Pos.CENTER);
        boxInfo.setAlignment(Pos.CENTER);

        this.setFitToWidth(true);
        this.setFitToHeight(true);

    }
}
