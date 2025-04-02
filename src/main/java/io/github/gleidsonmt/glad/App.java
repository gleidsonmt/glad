package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.controls.TextField;
import io.github.gleidsonmt.glad.controls.toggle_switch.ToggleSwitch;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.responsive.Break;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
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



//
//        TextFlow textFlow = new TextFlow(new Text("Lorem ipsum dolor color"));
//        textFlow.setMinWidth(300);
//        textFlow.getStyleClass().addAll( "border-warning", "text-warning", "border-l-2", "padding-5");
//        textFlow.setStyle("-fx-background-color: derive(-warning, 99%);");

//        action.getStyleClass().addAll("bg-danger", "round");
//        action.setGraphic(new SVGIcon(Icon.CHAT));
//        action.setCancelButton(true);

//        action.setOnAction(e -> {
//
//            Button ok = new Button("Button");
//            ButtonBar.setButtonData(ok, ButtonBar.ButtonData.OK_DONE);
//            Button cancel = new Button("Cancel");
//            cancel.setCancelButton(true);
//
//            Tooltip tooltip = new Tooltip("I will not die");
//            Tooltip.install(ok, tooltip);
//
//            ButtonBar.setButtonData(cancel, ButtonBar.ButtonData.CANCEL_CLOSE);
//            TextField textField = new TextField();
//            textField.getStyleClass().addAll("radius-6", "padding-10");
//            VBox box = new VBox(textFlow, ok, textField);
//            box.setPadding(new Insets(20));
//            root.behavior().alert().open("About", box, AlertType.INFO);
//
//            ok.getStyleClass().add("radius-8");
//            ok.setOnAction(i -> {
////                root.behavior().dialog().close();
//                root.wrapper().hide();
//                root.behavior().alert().close();
//            });
//
////            root.behavior().alert().open("Title", textFlow, AlertType.INFO);
////            root.behavior().dialog().open(textFlow);
//        });

    Container ct = new Container();
        Root root = new Root(new Layout(ct));

//        ToggleButton left = new ToggleButton("Left");
//        ToggleButton right = new ToggleButton("Right");
//        ToggleButton inverse = new ToggleButton("Inverse");
//        ToggleGroup group = new ToggleGroup();
//        group.getToggles().addAll(left, right, inverse);
//
//        left.getStyleClass().addAll("pill-left", "flat");
//        right.getStyleClass().addAll("pill-right", "flat");
//        inverse.getStyleClass().addAll("radius-0", "flat");
//        ct.getChildren().addAll(new HBox(left, inverse, right));

        Button flat = new Button("Button");
        flat.setGraphic(new SVGIcon(Icon.TODAY));
        flat.getStyleClass().add("bg-red border-red border-l-1");



        TextField textField = new TextField("Text Field");
        VBox box = new VBox(textField, flat);
        box.setSpacing(20);
        box.setPadding(new Insets(20));
        box.setAlignment(Pos.CENTER);

        ct.addPoint(e -> {

        }, Break.LG);


//        ComboBox<String> comboBox= new ComboBox<>();
//        comboBox.setItems(FXCollections.observableArrayList("Item 01", "Item 02"));


        ToggleSwitch _switch = new ToggleSwitch(true);
        textField.addClasses("radius-10 border-warning border-2 hover:border-warning focused:border-danger focused:border-5");
        VBox b = new VBox(_switch, textField);
        b.setAlignment(Pos.CENTER);
        ct.getChildren().addAll(b);
        Scene scene = new Scene(root, 800, 600);

        ThemeProvider.install(scene, Font.POPPINS, Font.INSTAGRAM);
        ThemeProvider.install(scene,
                Css.COLORS,
                Css.TYPOGRAPHIC,
                Css.SHAPES,
                Css.PROPERTIES,
                Css.BOOTSTRAP,
                Css.COMBO_BOX,
                Css.IMMERSIVE_SCROLL,
                Css.TAB_PANE,
                Css.PROGRESS_BAR,
                Css.HYPERLINK,
                Css.LIST_VIEW,
                Css.BUTTON,
                Css.CONTEXT_MENU,
                Css.TOGGLE_BUTTON,
                Css.TOGGLE_SWITCH,
                Css.TEXT_FIELD,
                Css.COMBO_BOX,
                Css.TABLE_VIEW
        );

//        label.setStyle("-fx-font-family: \"Instagram Sans Headline\"; -fx-font-size: 22px; ");

        stage.setScene(scene);
        stage.show();

        CSSFX.start(stage);
        ScenicView.show(stage.getScene());
    }

}

