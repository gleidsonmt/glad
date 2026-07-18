package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.skin.EditorSkin;
import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

/**
 * Basic class for editor boxes.
 * Editor is a wrapper of TextField it aims to be used inside a control.
 * Usually a box control, like (PasswordBox,  {@link TextBoxOld}) etc.
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  20/09/2022
 */
@SuppressWarnings("unused")
@DefaultProperty("children")
public class Editor extends TextField {

    // used for password text
    private final BooleanProperty maskText = new SimpleBooleanProperty(false);
    // used for max text input
    private final IntegerProperty max = new SimpleIntegerProperty(Integer.MAX_VALUE);
    private final StringProperty label = new SimpleStringProperty(this, "label");
    private final DoubleProperty spacing = new SimpleDoubleProperty(this, "spacing", 0);

    public final double getSpacing() {
        return spacing.get();
    }

    public final void setSpacing(double value) {
        spacing.set(value);
    }

    public final DoubleProperty spacingProperty() {
        return spacing;
    }


    public Editor() {
        getStyleClass().add("editor");
        setAlignment(Pos.CENTER_LEFT);

        addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (getLength() >= max.get()) {
                positionCaret(getLength());
                event.consume();
            }
        });
        maskTextProperty().addListener(this::changed);
    }

    @Override
    public void copy() {
        if (maskText.get()) return;
        super.copy();
    }

    @Override
    public void paste() {
        if (maskText.get()) return;

        final Clipboard clipboard = Clipboard.getSystemClipboard();

        if (clipboard.hasString()) {
            final String text = clipboard.getString();
            if (text != null) {
                int major = text.length();
                int comparator = max.get() - this.getLength();
                String sub;
                if ((major + this.getLength()) < max.get()) {
                    //
                    this.replaceSelection(text);
                } else {
                    sub = text.substring(0,
                            comparator);
                    this.replaceSelection(sub);
                }
            }
        }
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new EditorSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(Resources.getAgent("editor.css"));
    }

    public boolean isMaskText() {
        return maskText.get();
    }

    public BooleanProperty maskTextProperty() {
        return maskText;
    }

    public void setMaskText(boolean maskText) {
        this.maskText.set(maskText);
    }

    public int getMaxText() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void setMaxText(int max) {
        this.max.set(max);
    }

    private void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (newValue) addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
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
}
