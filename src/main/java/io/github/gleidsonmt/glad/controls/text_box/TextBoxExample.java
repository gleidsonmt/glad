package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.form.FormField;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.skin.TextBoxSkinExample;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 07/07/2026
 */
public class TextBoxExample extends TextField implements FormField {

    protected BooleanProperty action;
    private final StringProperty label = new SimpleStringProperty(this, "label");
    private final StringProperty helperText = new SimpleStringProperty(this, "helperText");
    private final IntegerProperty max = new SimpleIntegerProperty(this, "max", -1);
    private final ObjectProperty<Node> icon = new SimpleObjectProperty<>(this, "icon");
    protected final BooleanProperty valid = new SimpleBooleanProperty(this, "valid", true);

    protected static final StyleablePropertyFactory<TextBoxExample> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    protected final StyleableObjectProperty<Boolean> animated =
            new SimpleStyleableObjectProperty<>(ANIMATE, this, "animate", false);

    protected static final CssMetaData<TextBoxExample, Boolean> ANIMATE =
            FACTORY.createBooleanCssMetaData(
                    "-fx-animate",
                    g -> g.animated, false);

    protected static final PseudoClass PSEUDO_CLASS_ANIMATE = PseudoClass.getPseudoClass("animate");
    protected static final PseudoClass PSEUDO_CLASS_ERROR = PseudoClass.getPseudoClass("error");
    protected static final PseudoClass PSEUDO_CLASS_SUCCESS = PseudoClass.getPseudoClass("success");

    public TextBoxExample() {
        this((Icon) null, null);
    }

    public TextBoxExample(Icon icon) {
        this(icon, null);
    }

    public TextBoxExample(Node icon) {
        this(icon, null);
    }

    public TextBoxExample(String text) {
        this((Icon) null, text);
    }

    public TextBoxExample(Icon icon, String text) {
        this(icon != null ? new SVGIcon(icon) : null, text, false);
    }

    public TextBoxExample(Node icon, String text) {
        this(icon, text, false);
    }

    public TextBoxExample(Icon icon, String text, boolean action) {
        this(icon != null ? new SVGIcon(icon) : null, text, action);
    }

    public TextBoxExample(Node icon, String text, boolean action) {
        this.action = new SimpleBooleanProperty(action);
        getStyleClass().remove("text-field");
        getStyleClass().add("text-box");
        setText(text);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override
    public void validate() {
        pseudoClassStateChanged(PSEUDO_CLASS_ERROR, !isValid());
        ((TextBoxSkinExample) getSkin()).validate(isValid());
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
        return new TextBoxSkinExample(this, false);
    }

    public final Node getIcon() {
        return icon.get();
    }

    public final void setIcon(Node value) {
        icon.set(value);
    }

    public final ObjectProperty<Node> iconProperty() {
        return icon;
    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(Resources.class.getResource("agents/text-box.css")).toExternalForm();
//    }

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

//    public ReadOnlyIntegerProperty lengthProperty() {
//        return getEditor().lengthProperty();
//    }

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
}