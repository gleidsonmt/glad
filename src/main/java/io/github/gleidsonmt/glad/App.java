package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.dialog.DialogContainer;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Labeled;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/02/2025
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

//        TableView<User> tableView = new TableView<>();
//        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        TableColumn<User, String> firstColumn = new TableColumn<>("First column");
//        TableColumn<User, String> secondColumn = new TableColumn<>("Second column");
//
//        firstColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        secondColumn.setCellValueFactory(new PropertyValueFactory<>("legend"));
//
//        tableView.getItems().addAll(
//                new User("Gleidson", "Freelancer"),
//                new User("Jhon Doe", "Freelancer")
//        );
//
//        tableView.getColumns().addAll(firstColumn, secondColumn);

//        box.getChildren().setAll(tableView);
//        ListView<String> listView = new ListView<>();
//        listView.getItems().addAll(
//                "Line One",
//                "Line One",
//                "Line One",
//                "Line One"
//        );
//        box.getChildren().setAll(listView);

//        ProgressBar progressBar = new ProgressBar(0.5);
//        progressBar.getStyleClass().add("bg-success");
//        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
//
//        box.getChildren().setAll(progressBar);
//        Layout container = new Layout(
//                box
//        );

//        box.setPadding(new Insets(20));


        Container ct = new Container();
        Root root = new Root(new Layout(ct));

        Button action = new Button("Click me");
        ct.getChildren().add(action);

        TextFlow textFlow = new TextFlow(new Text("Lorem ipsum dolor color"));
        textFlow.setMinWidth(300);
        textFlow.getStyleClass().addAll( "border-warning", "text-warning", "border-l-2", "padding-5");
        textFlow.setStyle("-fx-background-color: derive(-warning, 99%);");

//        action.getStyleClass().addAll("bg-danger", "round");
        action.setGraphic(new SVGIcon(Icon.CHAT));
        action.setCancelButton(true);

        action.setOnAction(e -> {

            Button ok = new Button("Ok");
            ButtonBar.setButtonData(ok, ButtonBar.ButtonData.OK_DONE);
            Button cancel = new Button("Cancel");
            cancel.setCancelButton(true);

            ButtonBar.setButtonData(cancel, ButtonBar.ButtonData.CANCEL_CLOSE);
            VBox box = new VBox(textFlow, ok);
            box.setPadding(new Insets(20));
            root.behavior().alert().open("About", box, AlertType.INFO);

            ok.setOnAction(i -> {
//                root.behavior().dialog().close();
                root.wrapper().hide();
                root.behavior().alert().close();
            });

//            root.behavior().alert().open("Title", textFlow, AlertType.INFO);
//            root.behavior().dialog().open(textFlow);
        });



        Scene scene = new Scene(root, 800, 600);

        ThemeProvider.install(scene, Font.POPPINS, Font.INSTAGRAM);
        ThemeProvider.install(scene,
                Css.COLORS,
                Css.TYPOGRAPHIC,
                Css.SHAPES,
                Css.PROPERTIES,
                Css.BOOTSTRAP,
                Css.IMMERSIVE_SCROLL,
                Css.TAB_PANE,
                Css.PROGRESS_BAR,
                Css.HYPERLINK,
                Css.LIST_VIEW,
                Css.BUTTON,
                Css.TABLE_VIEW
        );

//        label.setStyle("-fx-font-family: \"Instagram Sans Headline\"; -fx-font-size: 22px; ");

        stage.setScene(scene);
        stage.show();

        CSSFX.start(stage);
        ScenicView.show(stage.getScene());
    }

}

