package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.TextBoxSkin;
import javafx.scene.control.TextField;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 05/08/2026
 */
public class PasswordBoxBehavior {

    public PasswordBoxBehavior(TextBoxSkin base, TextField editor) {
        var actionButton = new IconButton(new SVGIcon(Icon.VISIBILITY));
        actionButton.setFocusTraversable(false);
//        actionButton.setManaged(false);
        actionButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
//        base.setRightNode(actionButton);
        actionButton.setCancelButton(true);
        actionButton.setOnMouseClicked(_ -> {
            if (editor.getSkin() instanceof MaterialSkinTextField skin) {
                SVGIcon icon = (SVGIcon) actionButton.getGraphic();
                icon.setContent(skin.isShowPassword() ? Icon.VISIBILITY :  Icon.VISIBILITY_OFF);
                skin.showPasswordProperty().set(!skin.isShowPassword());
                base.getEditor().requestLayout();
            }
        });

        editor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!base.getSkinnable().isActionable()) return;
            if (newValue != null && !newValue.isEmpty()) {
                if (base.getContainer().getChildren().contains(actionButton)) return;
                base.getContainer().getChildren().addLast(actionButton);
            } else {
                base.getContainer().getChildren().remove(actionButton);
            }
        });
    }
}
