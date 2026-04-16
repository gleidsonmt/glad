package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.skin.TextBoxBase;
import io.github.gleidsonmt.glad.controls.skin.TextBoxSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/04/2025
 * Ultimate.
 */
public class TextBox extends TextBoxBase {

    protected BooleanProperty action;

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

        this.action = new SimpleBooleanProperty(action);

        getStyleClass().add("text-box");
        if (icon != null) setLeftNode(icon);
        setText(text);

//        if (action) setRightNode(createRightAction());

        // When editor has text clear button will appear
//        ChangeListener<String> hideAction = (_, _, newVal) -> {
//            setRightNode(newVal != null && !newVal.isEmpty() && isAction() ? createRightAction() : null);
//        };


    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new TextBoxSkin(this);
    }

    public void setIcon(Node node) {
        setLeftNode(node);
    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(Resources.class.getResource("agents/text-box.css")).toExternalForm();
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