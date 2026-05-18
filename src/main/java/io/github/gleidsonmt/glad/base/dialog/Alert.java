

package io.github.gleidsonmt.glad.base.dialog;

import io.github.gleidsonmt.glad.base.internal.DialogBase;
import io.github.gleidsonmt.glad.base.dialog.alert.AlertType;
import javafx.scene.control.Button;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  21/03/2025
 */
public interface Alert extends DialogBase<Alert> {

    // Build methods
    Alert type(AlertType alertType);

    Alert type(String alertType);

    @ApiStatus.Experimental
    Alert buttons(Button... buttons);

}
