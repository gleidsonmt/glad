package io.github.gleidsonmt.glad;

import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.responsive.*;
import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import io.github.gleidsonmt.glad.controls.avatar_crop.AvatarCrop;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/02/2025
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Container container = new Container(DefaultBreak.values());
        var title = createTile();
        container.getChildren().addAll(title);

        container.getBreakpoints().add(new BreakPoint(_ -> title.getStyleClass().remove("bg-red-500"), DefaultBreak.LG));
//        container.getBreakpoints().add(new BreakPoint(_ -> title.getStyleClass().add("bg-red-500"), DefaultBreak.MD));

        container.addBreakpoint(_ -> {
            title.getStyleClass().remove("bg-red-500");
        }, DefaultBreak.MD);

        container.getChildren().add(new AvatarCrop(Resources.getImage("avatar.jpg")));

        container.addBreakpoint(_ -> {
            title.getStyleClass().remove("bg-red-500");
        }, "LG");
        container.addBreakpoint(_ -> {
            title.getStyleClass().add("bg-red-500");
        }, ">MD");

        Scene scene = new Scene(container);
        ThemeProvider.install(scene, Css.BOOTSTRAP, Font.POPPINS, Css.DEFAULT);
        primaryStage.setScene(scene);
        primaryStage.show();


//        ScenicView.show(scene.getRoot());
    }

    private Node createTile() {
        Label tile = new Label("Tile");

        tile.getStyleClass().addAll("md:750");

        return tile;
    }


}

