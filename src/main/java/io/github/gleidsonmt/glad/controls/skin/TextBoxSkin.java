

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  24/02/2026
 */
public class TextBoxSkin extends TextBoxBaseSkin {

    private final IconButton clearButton;

    public TextBoxSkin(TextBox _control) {
        super(_control);
        this.clearButton = new IconButton(new SVGIcon(Icon.CLEAR), true);
        configAction(_control);

//        _control.actionProperty().addListener((_, _, newValue) -> setRightNode(newValue  ? createRightAction() : null));

        ChangeListener<Number> hideAction = (_, _, newVal) -> {
            _control.setRightNode(newVal.intValue() > 0 ? this.clearButton : null);
        };

        if (_control.isAction()) _control.getEditor().lengthProperty().addListener(hideAction);

        _control.editorProperty().addListener((_, oldVal, newVal) -> {
            if (newVal != null) {
                newVal.lengthProperty().addListener(hideAction);
            } else {
                if (oldVal != null) {
                    oldVal.lengthProperty().removeListener(hideAction);
                }
            }
        });
    }

    protected void configAction(TextBox control) {
        clearButton.setFocusTraversable(false);
        clearButton.setManaged(false);
        clearButton.setOnMouseClicked(createAction(control));
        clearButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
    }


    private @NotNull EventHandler<MouseEvent> createAction(TextBox control) {
        return _ -> {
            control.getEditor().clear();
            control.getEditor().requestFocus();
        };
    }
}
