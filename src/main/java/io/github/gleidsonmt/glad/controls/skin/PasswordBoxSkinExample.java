

package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.PasswordBoxExample;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  24/02/2026
 */
@Deprecated
public class PasswordBoxSkinExample extends TextBoxSkinExample {

    private final IconButton viewButton;

    private final PasswordField passwordField = new PasswordField();
    private final PassEditorSkin skin = new  PassEditorSkin(passwordField);

    class PassEditorSkin extends TextFieldSkin {

        private final BooleanProperty showPassword;

        public PassEditorSkin(PasswordField control) {
            super(control);

            this.showPassword = new SimpleBooleanProperty(true);
            passwordField.getStyleClass().add("editor");

            // Force the skin to refresh the text representation when the boolean toggles
            showPassword.addListener((obs, oldVal, newVal) -> {

                // Trick JavaFX into thinking the text changed so it invokes maskText()
                String currentText = passwordField.getText();
//                passwordField.setText(null);
                passwordField.setText(currentText);
//
//                // Move caret back to the end of the text
                passwordField.end();

            });


        }

        @Override
        protected String maskText(String txt) {
            if (showPassword == null) {
                return super.maskText(txt);
            }
            if (showPassword.get()) {
                return txt;
            }
            return super.maskText(txt);
        }
    }

    public PasswordBoxSkinExample(PasswordBoxExample _control) {
        super(_control, true);

        this.viewButton = new IconButton(new SVGIcon(Icon.VISIBILITY), true);
        configAction(_control);

        passwordField.setSkin(skin);

//        _control.actionProperty().addListener((_, _, newValue) -> setRightNode(newValue  ? createRightAction() : null));

        ChangeListener<Number> hideAction = (_, _, newVal) -> {
//            _control.setRightNode(newVal.intValue() > 0 ? this.viewButton : null);
            this.viewButton.setVisible(true);
        };


        if (_control.isAction()) passwordField.lengthProperty().addListener(hideAction);

//        _control.editorProperty().addListener((_, oldVal, newVal) -> {
//            if (newVal != null) {
//                passwordField.lengthProperty().addListener(hideAction);
//            } else {
//                if (oldVal != null) {
//                    passwordField.lengthProperty().removeListener(hideAction);
//                }
//            }
//        });

        textBox.setEditor(passwordField);
//        textBox.setRightNode(viewButton);

    }

    protected void configAction(PasswordBoxExample control) {
        viewButton.setFocusTraversable(false);
        viewButton.setManaged(false);
        viewButton.setOnMouseClicked(createAction(control));
        viewButton.getStyleClass().addAll("rounded", "max-w-30", "min-w-30", "min-h-30", "max-h-30");
    }

    private @NotNull EventHandler<MouseEvent> createAction(PasswordBoxExample control) {
        return _ -> {
            skin.showPassword.set(!skin.showPassword.get());
            viewButton.setGraphic(
                    new SVGIcon(
                            !skin.showPassword.get() ?
                                    Icon.VISIBILITY_OFF : Icon.VISIBILITY
                    )
            );
        };
    }
}
