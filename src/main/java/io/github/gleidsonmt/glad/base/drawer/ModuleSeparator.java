

package io.github.gleidsonmt.glad.base.drawer;

import io.github.gleidsonmt.glad.base.Module;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  10/04/2025
 */
public record ModuleSeparator(Node graphic, String name) implements Module {

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Node getGraphic() {
        return this.graphic;
    }

    @Override
    public boolean isAnimated() {
        return false;
    }
}
