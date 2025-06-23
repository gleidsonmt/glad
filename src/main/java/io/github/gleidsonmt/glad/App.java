package io.github.gleidsonmt.glad;

import io.github.gleidsonmt.glad.base.Launcher;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.theme.Css;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/02/2025
 */
public class App extends Launcher {

    @Override
    protected void build(Layout layout) {
        View main = new Main();
        layout.setView(main);

        addStyleSheets(Css.DEFAULT, Css.BUTTON);
    }

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//
//        Button test = new Button("Test");
//
//        test.setOnMouseClicked(e -> {
//            test.fireEvent(new ExecutionEventError(new Error("Error"), "Error on shalalala Error on shalalala Error on shalalala Error on shalalala Error on shalalala"));
//        });
//
//        Layout layout = new Layout(test);
//        RootImpl rootImpl = new RootImpl(layout);
//
//        rootImpl.getChildren();
//
//        Scene scene = new Scene(rootImpl, 800, 600);
//
//        scene.focusOwnerProperty().addListener(new ChangeListener<Node>() {
//            @Override
//            public void changed(ObservableValue<? extends Node> observableValue, Node node, Node t1) {
////                System.out.println("scene focus = " + t1);
//            }
//        });
//
////        ThemeProvider.install(scene, Font.INSTAGRAM);
//        ThemeProvider.install(scene, Font.POPPINS, Font.INSTAGRAM);
//
//        ThemeProvider.install(scene,
//                Css.DEFAULT,
//                Css.TYPOGRAPHIC,
//                Css.SHAPES,
//                Css.PROPERTIES,
//                Css.BOOTSTRAP,
//                Css.COMBO_BOX,
//                Css.IMMERSIVE_SCROLL,
//                Css.TAB_PANE,
//                Css.PROGRESS_BAR,
//                Css.HYPERLINK,
//                Css.LIST_VIEW,
//                Css.BUTTON,
//                Css.SPINNER,
//                Css.CONTEXT_MENU,
//                Css.TOGGLE_BUTTON,
//                Css.TOGGLE_SWITCH,
//                Css.TEXT_FIELD,
//                Css.CHECK_BOX,
//                Css.TREE_VIEW,
//                Css.RADIO_BUTTON,
//                Css.TEXT_BOX,
//                Css.DATE_PICKER,
//                Css.TABLE_VIEW
//        );
//
////        label.setStyle("-fx-font-family: \"Instagram Sans Headline\"; -fx-font-size: 22px; ");
//
//        stage.setScene(scene);
//        stage.show();
//
////        scene.getStylesheets().add(getClass().getResource("agents/editor.css").toExternalForm());
//
////        CSSFX.start(stage);
////        ScenicView.show(stage.getScene());
//    }

}

