

package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.skin.PasswordBoxSkin;
import io.github.gleidsonmt.glad.controls.skin.TextBoxBase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  04/04/2025
 * Ultimate.
 */
public class PasswordBox extends TextBox {

    private final IconButton hideButton;

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
        super(icon, text, action);
        setMaskText(true);
        this.hideButton = new IconButton(new SVGIcon(Icon.VISIBILITY), true);
        this.hideButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
        this.action = new SimpleBooleanProperty(action);

        getStyleClass().addAll("password-box", "text-box");
        if (icon != null) setLeftNode(icon);
        setText(text);

//        if (action) setRightNode(createRightAction());

        // When editor has text clear button will appear
//        ChangeListener<String> hideAction = (_, _, newVal) -> {
//            setRightNode(newVal != null && !newVal.isEmpty() && isAction() ? createRightAction() : null);
//        };

//        this.editorProperty().addListener((_, oldVal, newVal) -> {
//            if (newVal != null) {
//                newVal.textProperty().addListener(hideAction);
//            } else {
//                if (oldVal != null) {
//                    oldVal.textProperty().removeListener(hideAction);
//                }
//            }
//        });

    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new PasswordBoxSkin(this);
    }
}
