package io.github.gleidsonmt.glad.base.dialog.snack;

import io.github.gleidsonmt.glad.base.FlowItem;
import javafx.scene.Node;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2025
 */
public interface Snack extends FlowItem<Snack> {

    Snack message(String message);

    Snack graphic(Node graphic);

    Snack action(SnackOption... events);

}
