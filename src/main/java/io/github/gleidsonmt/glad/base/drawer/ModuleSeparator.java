

package io.github.gleidsonmt.glad.base.drawer;

import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  10/04/2025
 */
public record ModuleSeparator(Node graphic, String name)  {

    public String getName() {
        return this.name;
    }

    public Node getGraphic() {
        return this.graphic;
    }

    public boolean isAnimated() {
        return false;
    }
}
