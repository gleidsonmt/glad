

package io.github.gleidsonmt.glad.base.dialog.snack;

import io.github.gleidsonmt.glad.base.FlowItem;
import javafx.scene.Node;


/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  12/08/2025
 */
public interface Snack  {

    Snack message(String message);

    Snack graphic(Node graphic);

    Snack action(SnackOption... events);

    void show(String message);

    void show(Node graphic, String message, SnackOption... options);

    void show();
}
