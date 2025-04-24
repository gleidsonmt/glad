package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.base.internal.animations.Anchor;
import io.github.gleidsonmt.glad.base.internal.animations.Animations;
import io.github.gleidsonmt.glad.controls.avatar.AvatarStatus;
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

        Container ct = new Container();
        Layout layout = new Layout(ct);
        Root root = new Root(layout);

        TextField textField = new TextField("Text Field");

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

        Text text = new Text("Texto");
//        text.getStyleClass().addAll("font-instagram-medium");

        ToggleButton toggleButton = new ToggleButton("Button");
        VBox drawer = new VBox(text, toggleButton);
        drawer.getStyleClass().addAll("bg-yellow-500", "min-size-40");
        drawer.setPrefWidth(280);
        drawer.setPrefHeight(40);
        root.behavior().setDrawer(drawer);

        AvatarStatus avatarStatus = new AvatarStatus(Assets.getImage("avatar.jpg"));
        avatarStatus.setMinSize(60, 60);

        avatarStatus.setPrefHeight(40);
        avatarStatus.setPrefWidth(50);


        Button toggleSwitch = new Button();

        toggleSwitch.setOnMouseClicked(e -> {
            System.out.println("toggleSwitch.setOnMouseClicked");
            root.wrapper().show();
//            root.wrapper().setOnClick(ev -> {
//                System.out.println("what");
//                root.wrapper().hide();
//            });
        });

//        toggleSwitch.setStyle("""
//            -fx-color-animation: -fx-accent;
//            -fx-track-color:-light-gray;
//            -fx-arc-size: 20px;
//            -fx-track-size: 10px;
//        """);

        VBox b = new VBox(toggleSwitch);
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

