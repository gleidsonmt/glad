package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.PasswordBox;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/02/2026
 */
public class PasswordBoxSkin extends TextBoxBaseSkin {
    private final IconButton viewButton;

    public PasswordBoxSkin(PasswordBox _control) {
        super(_control);

        this.viewButton = new IconButton(new SVGIcon(Icon.VISIBILITY), true);
        configAction(_control);

//        _control.actionProperty().addListener((_, _, newValue) -> setRightNode(newValue  ? createRightAction() : null));

        ChangeListener<Number> hideAction = (_, _, newVal) -> {
            _control.setRightNode(newVal.intValue() > 0 ? this.viewButton : null );
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

    protected void configAction(PasswordBox control) {
        viewButton.setFocusTraversable(false);
        viewButton.setManaged(false);
        viewButton.setOnMouseClicked(createAction(control));
        viewButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
    }

    private @NotNull EventHandler<MouseEvent> createAction(PasswordBox control) {
        return _ -> {
            control.getEditor().setMaskText(!control.getEditor().isMaskText());
            viewButton.setGraphic(
                    new SVGIcon(
                            !control.getEditor().isMaskText() ?
                                    Icon.VISIBILITY_OFF : Icon.VISIBILITY
                    )
            );
            control.getEditor().setText(control.getText());
            control.getEditor().end();
        };
    }
}
