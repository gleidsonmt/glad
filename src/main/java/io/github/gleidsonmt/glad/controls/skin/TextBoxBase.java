/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.glad.controls.skin;

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

import java.util.List;

/**
 * Base class to construtc input field with a editor.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/09/2022
 */
@DefaultProperty("children")
public abstract class TextBoxBase extends Control {

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

        maskText.set(mask);
        getStyleClass().add("text-box-base");

        editorProperty().addListener((observable, oldValue, newValue) -> {

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
        if (animated.get()) {
            editor.set(new FloatEditor());
            pseudoClassStateChanged(PSEUDO_CLASS_ANIMATE, true);
        } else {
            editor.set(new Editor());
            pseudoClassStateChanged(PSEUDO_CLASS_ANIMATE, false);
        }
        return new TextBoxBaseSkin(this);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
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

    public Node getLeftNode() {
        return leftNode.get();
    }

    public ObjectProperty<Node> leftNodeProperty() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode.set(leftNode);
    }

    public Node getRightNode() {
        return rightNode.get();
    }

    public ObjectProperty<Node> rightNodeProperty() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode.set(rightNode);
    }

    public boolean isAnimate() {
        return animated.get();
    }

    public ObservableValue<Boolean> animateProperty() {
        return animated;
    }

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
