package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.drawer.Drawer;
import io.github.gleidsonmt.glad.base.drawer.Module;
import io.github.gleidsonmt.glad.responsive.Break;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/02/2025
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Container container = new Container(new Button("WElcome"));

        Drawer drawer = new Drawer(
                new Module("Controls",
                        new Module("Text"),
                        new Module("Label"),
                        new Module("Button")

                ),
                new Module("Layouts",
                        new Module("TitledPane"),
                        new Module("TabPane")

                )
        );

        container.setLeft(drawer);

        Root root = new Root(container);

        container.addPoint(point -> {

        }, Break.SM);

//        root.addPoint(point -> {
//            System.out.println(" my point ");
//        }, Break.SM, Break.LG);

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
                Css.HYPERLINK,
                Css.TABLE_VIEW
        );
        stage.setScene(new Scene(new Root(new Container(root)), 800, 600));
        stage.show();

        CSSFX.start(stage);
        ScenicView.show(stage.getScene());
    }
}
