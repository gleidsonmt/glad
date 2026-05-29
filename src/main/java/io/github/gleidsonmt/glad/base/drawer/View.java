

/*
 * *
 *  * Description:
 *  *
 *  * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 *  * Create on ${DATE}
 *
 */

package io.github.gleidsonmt.glad.base.drawer;

import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  19/07/2024
 */
@ApiStatus.Experimental
public class View extends Module {

    public View(String name) {
        this(name, null);
    }

    public View(String name, Node content) {
        super(name);
        this.content = content;
    }

    public View(String name, Node icon, Node content) {
        super(name);
        this.content = content;
        this.graphic = icon;
    }



    @Override
    public String toString() {
        return "{\"View\":"
               + super.toString()
               + ", \"content\":" + content
               + ", \"node\":" + getNode()
               + "}";
    }
}
