package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.GladResources;
import io.github.gleidsonmt.glad.controls.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.skin.GNTextBoxBase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/04/2025
 * Ultimate.
 */
public class PasswordBox extends GNTextBoxBase {

    private final IconButton hideButton;
    private BooleanProperty action;

    public PasswordBox() {
        this((Icon) null, null);
    }

    public PasswordBox(Icon icon) {
        this(icon, null);
    }

    public PasswordBox(String text) {
        this((Icon) null, text);
    }

    public PasswordBox(Icon icon, String text) {
        this(icon != null ? new SVGIcon(icon) : null, text, false);
    }

    public PasswordBox(Node icon, String text) {
        this(icon, text, false);
    }

    public PasswordBox(Icon icon, String text, boolean action) {
        this(icon != null ? new SVGIcon(icon) : null, text, action);
    }

    public PasswordBox(Node icon, String text, boolean action) {
        super(true);
        this.hideButton = new IconButton(Icon.CLEAR);
        this.action = new SimpleBooleanProperty(action);

        getStyleClass().addAll("password-box", "text-box");
        if (icon != null) setLeftNode(icon);
        setText(text);

        if (action) setRightNode(createRightAction());

        // When editor has text clear button will appear
        ChangeListener<String> hideAction = (_, _, newVal) -> setRightNode(newVal != null && !newVal.isEmpty() && isAction() ? hideButton : null);

        this.editorProperty().addListener((_, oldVal, newVal) -> {
            if (newVal != null) {
                newVal.textProperty().addListener(hideAction);
            } else {
                if (oldVal != null) {
                    oldVal.textProperty().removeListener(hideAction);
                }
            }
        });
    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(GladResources.class.getResource("agents/text-box.css")).toExternalForm();
    }

    private Node createRightAction() {
        hideButton.setFocusTraversable(false);
        hideButton.setManaged(false);
        hideButton.setOnMouseClicked(createAction());
        return hideButton;
    }

    private @NotNull EventHandler<MouseEvent> createAction() {
        return _ -> {
            getEditor().clear();
            getEditor().requestFocus();
        };
    }

    public boolean isAction() {
        return action.get();
    }

    public BooleanProperty actionProperty() {
        return action;
    }

    public void setAction(boolean action) {
        this.action.set(action);
    }
}
