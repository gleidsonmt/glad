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
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Control;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  06/11/2024
 */
public class AlertRoot extends StackPane {

    private static final StyleablePropertyFactory<AlertRoot> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableObjectProperty<Color> accentColor =
            new SimpleStyleableObjectProperty<>(ACCENT_COLOR, this, "icon", Color.WHITE);

    private static final CssMetaData<AlertRoot, Color> ACCENT_COLOR =
            FACTORY.createColorCssMetaData("accent-color", g -> g.accentColor);

    private final AlertWrapper container;

    public AlertRoot(AlertType type) {
        Node icon;
        this.getStyleClass().add("alert");

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
        this.container = new AlertWrapper(type);

//        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.getChildren().setAll(this.container, icon);

    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(Resources.class.getResource("css/alerts.css")).toExternalForm();
    }

    public void setContent(Node content) {
        this.container.getContent().setNode(content);
    }

    public ButtonBar getButtonBar() {
        return this.container.getContent().getButtonBar();
    }

    public void setTitle(String title) {
        this.container.getContent().getTitle().setText(title);
    }
}
