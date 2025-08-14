package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public interface Alert extends FlowItem<Alert> {

    // Fast methods
    void open(String title);

    void open(String title, AlertType alertType);

    void open(String title, Node node, AlertType alertType, Button... buttons);

    // Build methods
    Alert title(String title);

    Alert type(AlertType alertType);

    Alert effect(WrapperEffect effect);

    @ApiStatus.Experimental
    Alert buttons(Button... buttons);

    @ApiStatus.Experimental
    Snack snack(String message);


}
