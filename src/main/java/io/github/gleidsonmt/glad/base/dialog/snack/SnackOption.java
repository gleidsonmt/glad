

package io.github.gleidsonmt.glad.base.dialog.snack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  07/09/2025
 */
public record SnackOption(String message, EventHandler<ActionEvent> action) {

}
