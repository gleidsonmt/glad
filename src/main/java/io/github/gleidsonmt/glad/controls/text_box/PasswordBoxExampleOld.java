

package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  04/04/2025
 * Ultimate.
 */
@Deprecated
public class PasswordBoxExampleOld extends TextBoxExample {

    private final IconButton hideButton;

    public PasswordBoxExampleOld() {
        this((Icon) null, null);
    }

    public PasswordBoxExampleOld(Icon icon) {
        this(icon, null);
    }

    public PasswordBoxExampleOld(String text) {
        this((Icon) null, text);
    }

    public PasswordBoxExampleOld(Icon icon, String text) {
        this(icon != null ? new SVGIcon(icon) : null, text, false);
    }

    public PasswordBoxExampleOld(Node icon, String text) {
        this(icon, text, false);
    }

    public PasswordBoxExampleOld(Icon icon, String text, boolean action) {
        this(icon != null ? new SVGIcon(icon) : null, text, action);
    }

    public PasswordBoxExampleOld(Node icon, String text, boolean action) {
        super(icon, text, action);
//        setMaskText(true);
        this.hideButton = new IconButton(new SVGIcon(Icon.VISIBILITY), true);
        this.hideButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
        this.action = new SimpleBooleanProperty(action);

        getStyleClass().addAll("password-box", "text-box");
//        if (icon != null) setLeftNode(icon);
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

//    @Override
//    protected Skin<?> createDefaultSkin() {
//        return new PasswordBoxSkin(this);
//    }
}
