

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.form.FormField;
import io.github.gleidsonmt.glad.controls.text_box.Editor;
import io.github.gleidsonmt.glad.controls.text_box.FloatEditor;
import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

/**
 * Base class to construtc input field with a editor.
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  15/09/2022
 */
@DefaultProperty("children")
@SuppressWarnings("unused")
public abstract class TextBoxBase extends Control implements FormField {

    private final ObjectProperty<Editor> editor = new SimpleObjectProperty<>();
    private final ObjectProperty<Node> leftNode = new SimpleObjectProperty<>();
    private final ObjectProperty<Node> rightNode = new SimpleObjectProperty<>();

    private static final StyleablePropertyFactory<TextBoxBase> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableObjectProperty<Boolean> animated =
            new SimpleStyleableObjectProperty<>(ANIMATE, this, "animate", false);

    private static final CssMetaData<TextBoxBase, Boolean> ANIMATE =
            FACTORY.createBooleanCssMetaData(
                    "-fx-animate",
                    g -> g.animated, true);

    private static final PseudoClass PSEUDO_CLASS_ERROR = PseudoClass.getPseudoClass("error");
    private static final PseudoClass PSEUDO_CLASS_SUCCESS = PseudoClass.getPseudoClass("success");

    private final StringProperty helperText = new SimpleStringProperty();
    private final BooleanProperty valid = new SimpleBooleanProperty(this, "valid", true);

    private static final PseudoClass PSEUDO_CLASS_ANIMATE = PseudoClass.getPseudoClass("animate");

    private final ChangeListener<Boolean> retainFocus = (_, _, newValue) ->
            setFocused(newValue);

    private final BooleanProperty maskText = new SimpleBooleanProperty(true); // used for password text

    private final StringProperty text = new SimpleStringProperty();
    private final StringProperty promptText = new SimpleStringProperty();

    public TextBoxBase() {
        this(false);
    }

    protected TextBoxBase(boolean mask) {
        editor.set(new Editor());
        maskText.set(mask);
        getStyleClass().add("text-box-base");

        editorProperty().addListener((_, oldValue, newValue) -> {

            if (newValue != null) {

                newValue.focusedProperty().addListener(retainFocus);
                newValue.maskTextProperty().bindBidirectional(maskText);
                newValue.textProperty().bindBidirectional(text);
                newValue.promptTextProperty().bindBidirectional(promptText);

                if (oldValue != null)
                    oldValue.focusedProperty().removeListener(retainFocus);
            }

        });


    }

    @Override
    protected Skin<?> createDefaultSkin() {
//        if (animated.get()) {
//            editor.set(new FloatEditor());
//            pseudoClassStateChanged(PSEUDO_CLASS_ANIMATE, true);
//        } else {
//            editor.set(new Editor());
//            pseudoClassStateChanged(PSEUDO_CLASS_ANIMATE, false);
//        }
        return new TextBoxBaseSkin(this);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public String getHelperText() {
        return helperText.get();
    }

    public StringProperty helperTextProperty() {
        return helperText;
    }

    public void setHelperText(String helperText) {
        this.helperText.set(helperText);
    }

    @Override
    public void validate() {
        pseudoClassStateChanged(PSEUDO_CLASS_ERROR, !isValid());
        ((TextBoxBaseSkin) getSkin()).validate(isValid());
        valid.addListener((_, _, newValue) -> pseudoClassStateChanged(PSEUDO_CLASS_ERROR, !newValue));
    }

    @Override
    public boolean isValid() {
        return valid.get();
    }

    public void setValid(boolean valid) {
        this.valid.set(valid);
    }

    @Override
    public BooleanProperty validProperty() {
        return valid;
    }

    public Editor getEditor() {
        return editor.get();
    }

    protected ObjectProperty<Editor> editorProperty() {
        return editor;
    }

    protected void setEditor(Editor editor) {
        this.editor.set(editor);
    }

    protected Node getLeftNode() {
        return leftNode.get();
    }

    protected ObjectProperty<Node> leftNodeProperty() {
        return leftNode;
    }

    protected void setLeftNode(Node leftNode) {
        this.leftNode.set(leftNode);
    }

    protected Node getRightNode() {
        return rightNode.get();
    }

    protected ObjectProperty<Node> rightNodeProperty() {
        return rightNode;
    }

    protected void setRightNode(Node rightNode) {
        this.rightNode.set(rightNode);
    }

    @ApiStatus.Experimental
    public boolean isAnimate() {
        return animated.get();
    }

    @ApiStatus.Experimental
    public ObservableValue<Boolean> animateProperty() {
        return animated;
    }

    @ApiStatus.Experimental
    public void setAnimate(boolean animate) {
        this.animated.set(animate);
    }

    public boolean isMaskText() {
        return maskText.get();
    }

    public BooleanProperty maskTextProperty() {
        return maskText;
    }

    protected void setMaskText(boolean maskText) {
        this.maskText.set(maskText);
    }

    public final String getText() {
        return text.get();
    }

    public final void setText(String value) {
        text.set(value);
    }

    public final StringProperty textProperty() {
        return text;
    }

    public String getPromptText() {
        return promptText.get();
    }

    public StringProperty promptTextProperty() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText.set(promptText);
    }

}
