package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/06/2025
 */
public abstract class Launcher extends Application {

    private Root root;
    private Layout layout;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        LayoutImpl layoutImpl = new LayoutImpl();
        Root root = new RootImpl(layoutImpl);
        scene  = new Scene((Parent) root, 800, 600);
        this.stage = stage;
        build(layoutImpl);
        stage.setScene(scene);
        stage.show();

    }

    protected void addStyleSheets(Css... csses) {
        ThemeProvider.install(scene, csses);
    }

    public Scene getScene() {
        return scene;
    }

    protected void addStyleSheets(String... csses) {
        scene.getStylesheets().addAll(csses);
    }

    protected void addFonts(Font... fonts) {
        ThemeProvider.install(scene, fonts);
    }

    protected void addIcons(Image... icons) {
        this.stage.getIcons().addAll(icons);
    }

    protected abstract void build(Layout layout);

}
