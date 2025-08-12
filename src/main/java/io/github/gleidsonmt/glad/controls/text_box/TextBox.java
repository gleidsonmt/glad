package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.skin.TextBoxBase;
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
public final class TextBox extends TextBoxBase {

    private final IconButton clearButton;
    private BooleanProperty action;

    public TextBox() {
        this((Icon) null, null);
    }

    public TextBox(Icon icon) {
        this(icon, null);
    }

    public TextBox(Node icon) {
        this(icon, null);
    }

    public TextBox(String text) {
        this((Icon) null, text);
    }

    public TextBox(Icon icon, String text) {
        this(icon != null ? new SVGIcon(icon) : null, text, false);
    }

    public TextBox(Node icon, String text) {
        this(icon, text, false);
    }

    public TextBox(Icon icon, String text, boolean action) {
        this(icon != null ? new SVGIcon(icon) : null, text, action);
    }

    public TextBox(Node icon, String text, boolean action) {
        super(false);
        this.clearButton = new IconButton(new SVGIcon(Icon.CLEAR), true);
        this.clearButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
        this.action = new SimpleBooleanProperty(action);

        getStyleClass().add("text-box");
        if (icon != null) setLeftNode(icon);
        setText(text);

        if (action) setRightNode(createRightAction());

        // When editor has text clear button will appear
        ChangeListener<String> hideAction = (_, _, newVal) -> {
            setRightNode(newVal != null && !newVal.isEmpty() && isAction() ? createRightAction() : null);
        };

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
        return Objects.requireNonNull(Resources.class.getResource("agents/text-box.css")).toExternalForm();
    }

    private Node createRightAction() {
        clearButton.setFocusTraversable(false);
        clearButton.setManaged(false);
        clearButton.setOnMouseClicked(createAction());
        return clearButton;
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
