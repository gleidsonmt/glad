package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.base.internal.animations.Anchor;
import io.github.gleidsonmt.glad.base.internal.animations.Animations;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import io.github.gleidsonmt.glad.controls.avatar.StackedAvatar;
import io.github.gleidsonmt.glad.controls.badge.Badge;
import io.github.gleidsonmt.glad.controls.button.Button;
import io.github.gleidsonmt.glad.controls.button.FabButton;
import io.github.gleidsonmt.glad.controls.text_box.PasswordBox;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import io.github.gleidsonmt.glad.controls.TextField;
import io.github.gleidsonmt.glad.controls.enums.FloatAlignment;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.text_box.Editor;
import io.github.gleidsonmt.glad.controls.text_box.FloatEditor;
import io.github.gleidsonmt.glad.controls.toggle_switch.ToggleSwitch;
import io.github.gleidsonmt.glad.responsive.Break;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    Layout layout = new Layout(ct);
        Root root = new Root(layout);

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

        javafx.scene.control.Button flat = new javafx.scene.control.Button("Button");
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


//        ToggleSwitch _switch = new ToggleSwitch(true);
//        textField.addClasses("radius-10 border-warning border-2 hover:border-warning focused:border-danger focused:border-5");

// create the tree model
        CheckBoxTreeItem<String> jonathanGiles = new CheckBoxTreeItem<>("Jonathan");
        jonathanGiles.setIndeterminate(true);
        CheckBoxTreeItem<String> juliaGiles = new CheckBoxTreeItem<>("Julia");
        CheckBoxTreeItem<String> mattGiles = new CheckBoxTreeItem<>("Matt");
        CheckBoxTreeItem<String> sueGiles = new CheckBoxTreeItem<>("Sue");
        CheckBoxTreeItem<String> ianGiles = new CheckBoxTreeItem<>("Ian");

        CheckBoxTreeItem<String> gilesFamily = new CheckBoxTreeItem<>("Giles Family");
        gilesFamily.setExpanded(true);
        gilesFamily.getChildren().addAll(jonathanGiles, juliaGiles, mattGiles, sueGiles, ianGiles);

        // create the treeView
        final TreeView<String> treeView = new TreeView<>();
        treeView.setRoot(gilesFamily);

        // set the cell factory
        treeView.setCellFactory(CheckBoxTreeCell.forTreeView());
//        treeView.setCellFactory(Radio);

        CheckBox checkBox = new CheckBox();
//        checkBox.setAllowIndeterminate(true);
//        checkBox.setIndeterminate(true);
//        checkBox.setSelected(true);

        ChoiceBox<String> contextMenu = new ChoiceBox<>();
        contextMenu.setItems(FXCollections.observableArrayList(
                "Item 01",
                "Item 01",
                "Item 01",
                "Item 01"
        ));

        Editor editor = new Editor();
//        editor.setMaskText(true);
//        editor.setMaxText(12);
        FloatEditor floatEditor = new FloatEditor();
        floatEditor.setFloatAlignment(FloatAlignment.TOP);
        floatEditor.setPromptText("Float Text");
//        floatEditor.setText("Text");

        SVGIcon icon = new SVGIcon(Icon.ACCOUNT);
        TextBox textBox = new TextBox(icon);
        textBox.setMinHeight(40);
        textBox.setPromptText("Insert your name");
        textBox.setStyle("-fx-animate: true;");
        textBox.setAction(true);

        PasswordBox passBox = new PasswordBox(Icon.VPN_KEY_FILLED);
        passBox.setPromptText("Insert your Text");
        passBox.setStyle("-fx-animate: true;");
        passBox.setMinHeight(40);
        passBox.setAction(true);

        Badge badge = new Badge();
        badge.setIcon(new SVGIcon(Icon.APPS));
        badge.setNumberOfNotifications(10);
        badge.setMaxNotifications(20);
        badge.setStyle("-fx-box-color: -danger; -fx-type: rounded;");
//        badge.getStyleClass().add("rounded");

        FabButton iconButton = new FabButton(Icon.TODAY, true);
        iconButton.setStyle("-fx-icon: UNDO;");

//        VBox b = new VBox( badge, editor, floatEditor, textBox, passBox, new Button(), new javafx.scene.control.Button("Button"), iconButton, new IconButton(new SVGIcon(Icon.CALENDAR_MONTH), true));
        Text text = new Text("Texto");

//        text.getStyleClass().addAll("font-instagram-medium");

        ToggleButton toggleButton = new ToggleButton("Button");
        VBox drawer = new VBox(text, toggleButton);
        drawer.getStyleClass().addAll("bg-yellow-500", "min-size-40");
        drawer.setPrefWidth(280);
        drawer.setPrefHeight(40);
        root.behavior().setDrawer(drawer);

        badge.getStyleClass().addAll("min-size-50");
        Button ac = new Button("Action");
        ac.setOnAction(e -> {

            Root main = (Root) ac.getScene().getRoot();
            main.behavior().openDrawer();
//            main.flow()
////                    .insets(new Insets(20))
//                    .pos(Pos.TOP_CENTER)
//                    .content(drawer)
////                    .anchor(Anchor.RIGHT)/
//                    .show();

//            Timeline timeline = new Timeline();
//            Insets insets = StackPane.getMargin(drawer);
//            timeline.getKeyFrames().setAll(
//                    new KeyFrame(Duration.ZERO, new KeyValue(
//                            drawer.translateYProperty(), drawer.getHeight() + insets.getBottom()
//                    )),
//                    new KeyFrame(Duration.millis(200), new KeyValue(
//                            drawer.translateYProperty(), 0
//                    ))
//            );

//            timeline.play();
//            main.flow().openAbsolute(
//                    Pos.BOTTOM_CENTER,
//                    drawer,
//                    new Insets(20),
//                    Animations.APPEAR_FROM_BOTTOM);
        });

        ToggleSwitch one = new ToggleSwitch(true);
        one.setStyle("    -fx-arc-size: 25px;\n" +
                     "    -fx-track-size: 25px;");
        ToggleSwitch two = new ToggleSwitch(true);
        two.setStyle("    -fx-arc-size: 0px;\n" +
                     "    -fx-track-size: 25px;");
        ToggleSwitch three = new ToggleSwitch(true);
        three.setStyle("    -fx-arc-size: 0px;\n" +
                     "    -fx-track-size: 5px;");

        Button button = new Button("Default");
        Button disable = new Button("Disable");
        disable.setDisable(true);
        Button cancel = new Button("Cancel");
        cancel.setCancelButton(true);
        Button _flat = new Button("Flat");
        _flat.getStyleClass().add("flat");

        StackedAvatar stackedAvatar = new StackedAvatar();

        stackedAvatar.setMax(2);

        stackedAvatar.getAvatarViews().addAll(
                new AvatarView(Assets.getImage("avatar.jpg")),
                new AvatarView(Assets.getImage("avatar.jpg")),
                new AvatarView(Assets.getImage("avatar.jpg")),
                new AvatarView(Assets.getImage("avatar.jpg")),
                new AvatarView(Assets.getImage("avatar.jpg"))
        );




        Button add = new Button("Add");
        add.setOnAction(e -> {
            stackedAvatar.getAvatarViews().addAll(
                    new AvatarView(Assets.getImage("avatar.jpg"), 10)
            );
        });

        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            stackedAvatar.getAvatarViews().removeLast();
        });

        Button update = new Button("Update");
        update.setOnAction(e -> {
//            stackedAvatar.getAvatarViews().set(2, new AvatarView(Assets.getImage("brazil.png")));
            stackedAvatar.setMax(4);
            stackedAvatar.setAvatarRadius(100);
        });


        Button update1 = new Button("Update Max");
        update1.setOnAction(e -> {
//            stackedAvatar.getAvatarViews().set(2, new AvatarView(Assets.getImage("brazil.png")));
//            stackedAvatar.setMax(3);
            stackedAvatar.setAvatarSize(60);
        });


        VBox b = new VBox( textBox, passBox, update1);
        b.setPadding(new Insets(20));
        b.setSpacing(10);
        b.setAlignment(Pos.CENTER);
        ct.getChildren().addAll(b);
        Scene scene = new Scene(root, 800, 600);

        scene.focusOwnerProperty().addListener(new ChangeListener<Node>() {
            @Override
            public void changed(ObservableValue<? extends Node> observableValue, Node node, Node t1) {
//                System.out.println("scene focus = " + t1);
            }
        });

        ThemeProvider.install(scene, Font.POPPINS, Font.INSTAGRAM);
        ThemeProvider.install(scene,
                Css.DEFAULT,
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
                Css.CHECK_BOX,
                Css.TREE_VIEW,
                Css.RADIO_BUTTON,
                Css.TEXT_BOX,
                Css.TABLE_VIEW
        );

//        label.setStyle("-fx-font-family: \"Instagram Sans Headline\"; -fx-font-size: 22px; ");

        stage.setScene(scene);
        stage.show();

//        scene.getStylesheets().add(getClass().getResource("agents/editor.css").toExternalForm());

        CSSFX.start(stage);
        ScenicView.show(stage.getScene());
    }

}

