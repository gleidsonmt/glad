package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/02/2025
 */
public class PresentationDemo extends Application {

    @Override
    public void start(Stage stage) {

        Layout container = new Layout();
        Root root = new Root(container);

        ThemeProvider.install(root, Font.POPPINS);
        ThemeProvider.install(root,
                Css.COLORS,
                Css.TYPOGRAPHIC,
                Css.SHAPES,
                Css.PROPERTIES,
                Css.BOOTSTRAP,
                Css.IMMERSIVE_SCROLL,
                Css.TAB_PANE,
                Css.PROGRESS_BAR,
                Css.HYPERLINK
        );

//        container.setCenter(
//                new Presentation()
//                        .h1("Headline 1")
//                        .h2("Headline 2")
//                        .h3("Headline 3")
//                        .h4("Headline 4")
//                        .h5("Headline 5")
//                        .h6("Headline 6")
//                        .text("Lorem ipsum dolor color")
//                        .image(new Image(Assets.getImage("avatar.jpg")))
//                        .demonstration(new Button("Welcome"))
//                        .demo(new Button("Welcome"))
//                        .demonstration(List.of(new Button("Ok")), Assets.getResourceAsStream("button.txt"))
//
//                        .build()
//                        .getRoot()
//        );

        stage.setScene(new Scene(root, 800, 600));

//        stage.getScene().getStylesheets().add(Objects.requireNonNull(GladResources.class.getResource("fonts/poppins.css")).toExternalForm());
//        stage.getScene().getStylesheets().add(Objects.requireNonNull(GladResources.class.getResource("css/typographic.css")).toExternalForm());
//        stage.getScene().getStylesheets().add(Objects.requireNonNull(GladResources.class.getResource("css/colors.css")).toExternalForm());

        stage.show();
        ScenicView.show(stage.getScene());
        CSSFX.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
