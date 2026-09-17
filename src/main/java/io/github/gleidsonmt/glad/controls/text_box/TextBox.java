package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.property.*;
import javafx.css.*;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 24/08/2026
 */
public class TextBox extends Control {

    private final StringProperty label = new SimpleStringProperty(this, "Label", "");
    private final StringProperty suggestedText = new SimpleStringProperty(this, "Suggested Text");
    private final ObjectProperty<Node> icon = new SimpleObjectProperty<>(this, "icon");
    private final IntegerProperty max = new SimpleIntegerProperty(this, "max", -1);
    private final ObjectProperty<TextField> editor = new SimpleObjectProperty<>(this, "editor");

    protected static final StyleablePropertyFactory<TextBox> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    protected final StyleableObjectProperty<Boolean> animated =
            new SimpleStyleableObjectProperty<>(ANIMATE, this, "animate", false);

    protected final StyleableObjectProperty<Boolean> actionable =
            new SimpleStyleableObjectProperty<>(ACTIONABLE, this, "animate", false);

    protected static final CssMetaData<TextBox, Boolean> ACTIONABLE =
            FACTORY.createBooleanCssMetaData(
                    "-fx-actionable",
                    g -> g.actionable, false);

    protected static final CssMetaData<TextBox, Boolean> ANIMATE =
            FACTORY.createBooleanCssMetaData(
                    "-fx-animate",
                    g -> g.animated, false);

    protected static final PseudoClass PSEUDO_CLASS_ANIMATE = PseudoClass.getPseudoClass("animate");


    public TextBox() {
        this(Icon.NONE, null, false);
    }

    public TextBox(String label) {
       this(Icon.NONE, label, false);
    }

    public TextBox(Icon icon) {
        this(icon, null, false);
    }

    public TextBox(Icon icon, String label, boolean action) {
        this(new SVGIcon(icon), label, action);
    }

    public TextBox(Node icon, String label, boolean action) {
        getStyleClass().add("text-box");
        this.label.set(label);
        this.icon.set(icon);
        editor.set(new TextField());
        animated.addListener((_, _, newVal) -> pseudoClassStateChanged(PSEUDO_CLASS_ANIMATE, newVal));
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new TextBoxSkin(this, editor.get());
    }

    public StringProperty textProperty() {
        return editor.get().textProperty();
    }

    public String getText() {
        return editor.get().getText();
    }

    public void setText(String text) {
        editor.get().setText(text);
    }

    public TextField getEditor() {
        return editor.get();
    }


//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(getClass().getResource("button_list.css")).toExternalForm();
//    }



    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public Node getIcon() {
        return icon.get();
    }

    public void setIcon(Node icon) {
        this.icon.set(icon);
    }

    public ObjectProperty<Node> iconProperty() {
        return this.icon;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public String getLabel() {
        return label.get();
    }

    public StringProperty labelProperty() {
        return label;
    }

    public boolean isAnimate() {
        return animated.get();
    }

    public BooleanProperty animateProperty() {
        return BooleanProperty.booleanProperty(animated);
    }

    public boolean isActionable() {
        return actionable.get();
    }

    public BooleanProperty actionableProperty() {
        return BooleanProperty.booleanProperty(actionable);
    }

    public void setActionable(boolean actionable) {
        this.actionable.set(actionable);
    }

    public void setAnimate(boolean animate) {
        this.animated.set(animate);
    }

    public StringProperty suggestedProperty() {
        return suggestedText;
    }

    public void setSuggested(String suggested) {
        this.suggestedText.set(suggested);
    }

    public String getSuggested() {
        return suggestedText.get();
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
