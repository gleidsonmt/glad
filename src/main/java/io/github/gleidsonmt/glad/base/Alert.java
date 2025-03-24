package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public interface Alert {

    void open(String title);

    void open(String title, AlertType alertType);

    void open(String title, Node node, AlertType alertType, Button... buttons);

    void close();
}
