package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.responsive.Break;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/02/2025
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        VBox box = new VBox();

        Container container = new Container(
                box
        );



        Root root = new Root(container);

        Label label = new Label("Welcome to javafx");
        box.getChildren().add(label);
        label.setGraphic(new SVGIcon(Icon.TODAY));
        label.getStyleClass().add("lbl");


        container.addPoint(point -> {

        }, Break.SM);

//        root.addPoint(point -> {
//            System.out.println(" my point ");
//        }, Break.SM, Break.LG);

        ThemeProvider.install(root, Font.POPPINS, Font.INSTAGRAM);
        ThemeProvider.install(root,
                Css.COLORS,
                Css.TYPOGRAPHIC,
                Css.SHAPES,
                Css.PROPERTIES,
                Css.BOOTSTRAP,
                Css.IMMERSIVE_SCROLL,
                Css.TAB_PANE,
                Css.PROGRESS_BAR,
                Css.HYPERLINK,
                Css.TABLE_VIEW
        );

//        label.setStyle("-fx-font-family: \"Instagram Sans Headline\"; -fx-font-size: 22px; ");
        label.getStyleClass().add("lbl-test");

        stage.setScene(new Scene(new Root(new Container(root)), 800, 600));
        stage.show();

        CSSFX.start(stage);
        ScenicView.show(stage.getScene());
    }

    private TabPane createDemo(Side side) {
        TabPane tabPane = new TabPane();
        tabPane.setSide(side);
        Tab one = new Tab("Example 01");
        Tab two =new Tab("Example 02");
        tabPane.getTabs().addAll(one, two);
        return tabPane;
    }
}
