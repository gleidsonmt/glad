

package io.github.gleidsonmt.glad.controls.skin;

import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.css.*;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

/**
 * Base class to construtc input field with a editor.
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  15/09/2022
 */
@DefaultProperty("children")
@SuppressWarnings("unused")
@Deprecated(forRemoval = true)
public class TextBoxBase extends Control {

    protected final ObjectProperty<TextField> editor = new SimpleObjectProperty<>();
    protected final ObjectProperty<Node> leftNode = new SimpleObjectProperty<>();
    protected final ObjectProperty<Node> rightNode = new SimpleObjectProperty<>();

    protected static final StyleablePropertyFactory<TextBoxBase> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    protected final StyleableDoubleProperty spacing =
            new SimpleStyleableDoubleProperty(SPACING, this, "spacing", 8.0);


    protected static final CssMetaData<TextBoxBase, Number> SPACING =
            FACTORY.createSizeCssMetaData("-fx-spacing", s -> s.spacing, 8.0);


    protected final StringProperty helperText = new SimpleStringProperty();


    protected final ChangeListener<Boolean> retainFocus = (_, _, newValue) ->
            setFocused(newValue);

    protected final BooleanProperty maskText = new SimpleBooleanProperty(true); // used for password text

    protected final StringProperty text = new SimpleStringProperty();
    @Deprecated(forRemoval = true)
    protected final StringProperty promptText = new SimpleStringProperty();

    public TextBoxBase(TextField editor) {
        setEditor(editor);
//        maskText.set(mask);
        getStyleClass().add("text-box-base");
//        editor.getStyleClass().add("editor");

        editorProperty().addListener((_, oldValue, newValue) -> {

            if (newValue != null) {

                newValue.focusedProperty().addListener(retainFocus);
                text.bindBidirectional(newValue.textProperty());
                newValue.promptTextProperty().bindBidirectional(promptText);

                if (oldValue != null)
                    oldValue.focusedProperty().removeListener(retainFocus);
            }

            if (oldValue != null) {
                text.unbindBidirectional(oldValue.textProperty());
            }
        });
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new TextBoxBaseSkin(this);
    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(Resources.class.getResource("agents/text-box-base.css")).toExternalForm();
//    }

    public StyleableDoubleProperty spacingProperty() {
        return spacing;
    }

    public double getSpacing() {
        return spacing.get();
    }

    public void setSpacing(double val) {
        spacing.set(val);
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


    public TextField getEditor() {
        return editor.get();
    }

    protected ObjectProperty<TextField> editorProperty() {
        return editor;
    }

    protected void setEditor(TextField editor) {
        this.editor.set(editor);
    }

    protected Node getLeftNode() {
        return leftNode.get();
    }

    protected ObjectProperty<Node> leftNodeProperty() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode.set(leftNode);
    }

    protected Node getRightNode() {
        return rightNode.get();
    }

    protected ObjectProperty<Node> rightNodeProperty() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode.set(rightNode);
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
