package io.github.gleidsonmt.glad.base.dialog.snack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/09/2025
 */
public record SnackItem(String message, EventHandler<ActionEvent> action) {

}
