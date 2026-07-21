package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.skin.TextBoxSkin;
import io.github.gleidsonmt.glad.controls.skin.TextBoxBase;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 07/07/2026
 */
public class TextBox extends TextBoxBase {

    protected BooleanProperty action;
    private final StringProperty label = new SimpleStringProperty(this, "label");
    private final StringProperty helperText = new SimpleStringProperty(this, "helperText");
    private final IntegerProperty max = new SimpleIntegerProperty(this, "max", -1);

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
        super(new TextField());

        this.action = new SimpleBooleanProperty(action);

        getStyleClass().remove("text-box-base");
        getStyleClass().add("text-box");
        if (icon != null) setLeftNode(icon);
        setText(text);


//        if (action) setRightNode(createRightAction());

        // When editor has text clear button will appear
//        ChangeListener<String> hideAction = (_, _, newVal) -> {
//            setRightNode(newVal != null && !newVal.isEmpty() && isAction() ? createRightAction() : null);
//        };

    }

    public final String getHelperText() {
        return helperText.get();
    }

    public final void setHelperText(String value) {
        helperText.set(value);
    }

    public final StringProperty helperTextProperty() {
        return helperText;
    }


    public final String getLabel() {
        return label.get();
    }

    public final void setLabel(String value) {
        label.set(value);
    }

    public final StringProperty labelProperty() {
        return label;
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

    public void setMax(int max) {
        this.max.set(max);
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }
}
