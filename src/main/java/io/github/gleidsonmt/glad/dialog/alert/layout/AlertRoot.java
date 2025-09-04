package io.github.gleidsonmt.glad.dialog.alert.layout;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import io.github.gleidsonmt.glad.dialog.alert.icons.ErrorIcon;
import io.github.gleidsonmt.glad.dialog.alert.icons.InfoIcon;
import io.github.gleidsonmt.glad.dialog.alert.icons.SuccessIcon;
import io.github.gleidsonmt.glad.dialog.alert.icons.WarnIcon;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleablePropertyFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  06/11/2024
 */
public class AlertRoot extends VBox {

    private final Text title ;
    private Node content;
    private final BarAction barAction;
    private final VBox barContainer;

    public AlertRoot(String title, Node node, AlertType type) {
        Node icon;
        this.title = new Text(title);
        this.title.getStyleClass().addAll("h3 font-instagram-headline".split(" "));
        this.content = node;
        this.barAction = new BarAction();
        this.barContainer = new VBox(barAction);
        this.barContainer.getStyleClass().add("bar-container");
        this.getStyleClass().add("alert");
        setAlignment(Pos.CENTER);
        VBox.setVgrow( barContainer, Priority.ALWAYS);

        switch (type) {
            case ERROR -> {
                this.getStyleClass().add("alert-error");
                icon = new ErrorIcon();
            }

            case SUCCESS -> {
                this.getStyleClass().add("alert-success");
                icon = new SuccessIcon();
            }
            case WARNING -> {
                this.getStyleClass().add("alert-warn");
                icon = new WarnIcon();
            }

            case null, default -> {
                this.getStyleClass().add("alert-info");
                icon = new InfoIcon();
            }
        }

//        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMinWidth(300);
        this.setMaxHeight(Region.USE_PREF_SIZE);
        this.setPadding(new Insets(20));
//        this.container = new AlertWrapper(type);

//        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.getChildren().setAll(icon, this.title, content, barContainer);
        getStylesheets().add(Objects.requireNonNull(Resources.class.getResource("css/alerts.css")).toExternalForm());

    }


//    public void setContent(Node content) {
//        this.container.getContent().setNode(content);
//    }
//
//    public ButtonBar getButtonBar() {
//        return this.container.getContent().getButtonBar();
//    }
//
//    public void setTitle(String title) {
//        this.container.getContent().getTitle().setText(title);
//    }

    public ButtonBar getButtonBar() {
        return this.barAction;
    }
}
