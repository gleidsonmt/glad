package io.github.gleidsonmt.glad;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.notifications.CommentNotification;
import io.github.gleidsonmt.glad.notifications.FollowNotification;
import io.github.gleidsonmt.glad.notifications.InviteNotification;
import io.github.gleidsonmt.glad.notifications.component.NotificationItem;
import io.github.gleidsonmt.glad.notifications.factory.NotificationManager;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/02/2025
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        GridPane navBar = new GridPane();
        Button button = new Button("Button");
        navBar.add(button, 0,0);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setHalignment(button, HPos.RIGHT);



        Container container = new Container();
        Layout layout = new Layout(container);
        layout.setTop(navBar);
        Root root = new Root(layout);

        NotificationManager manager = new NotificationManager();

        manager.addAll(
                new NotificationItem<>(
                        new FollowNotification(
                                new User(
                                        Assets.getImage("avatar.jpg"),
                                        "@gleidsonmt", "Gleidson Neves"
                                ),
                                LocalDateTime.of(LocalDate.of(2024, 11, 2), LocalTime.of(14, 23)),
                                false
                        )
                ),
                new NotificationItem<>(
                        new FollowNotification(new User(Assets.getImage("avatar.jpg"), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 2, 22), LocalTime.of(12, 12)), false)
                ),
                new NotificationItem<>(
                        new CommentNotification(new  User(Assets.getImage("avatar.jpg"), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 2, 22), LocalTime.of(12, 12)), false,
                                "Love the background on this! Wold love to learn how to create the mesh gradient effect.")
                ),
                new NotificationItem<>(
                        new InviteNotification(new User(Assets.getImage("avatar.jpg"), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 3, 05), LocalTime.of(12, 12)), false)
                ),
                new NotificationItem<>(
                        new FollowNotification(new User(Assets.getImage("avatar.jpg"), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 2, 22), LocalTime.of(12, 12)), false)
                )
        );

        manager.getRoot().setMinWidth(600);
        button.setOnMouseClicked(e -> {
            System.out.println("root.flow().fits(manager.getRoot()) = " + root.flow().fits(manager.getRoot()));
            root.flow().openByCursor(manager.getRoot(), e);
        });


        Scene scene = new Scene(root, 800, 600);

        scene.focusOwnerProperty().addListener(new ChangeListener<Node>() {
            @Override
            public void changed(ObservableValue<? extends Node> observableValue, Node node, Node t1) {
//                System.out.println("scene focus = " + t1);
            }
        });

        ThemeProvider.install(scene, Font.INSTAGRAM);
//        ThemeProvider.install(scene, Font.POPPINS, Font.INSTAGRAM);

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

//        CSSFX.start(stage);
//        ScenicView.show(stage.getScene());
    }

}

