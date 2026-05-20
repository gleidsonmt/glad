

package io.github.gleidsonmt.glad.base;

import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  19/07/2024
 */
@ApiStatus.AvailableSince("1.0.0")
public class View extends ModuleView {

    protected Node content;
    protected Node icon;

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
        this.icon = icon;
    }

    public View(String name, Node content, ModuleView... children) {
        super(name, children);
        this.content = content;
    }

    public View(String name, Node icon, Node content, ModuleView... children) {
        super(name, children);
        this.content = content;
        this.icon = icon;
    }

    @Override
    public Node getGraphic() {
        return this.icon;
    }

    public void setContent(Node content) {
        this.content = content;
    }

    public Node getContent() {
        return content;
    }

    public Root getRoot() {
        return (Root) this.getContent().getScene().getRoot();
    }

    @Override
    public String toString() {
        return "View{" + "\n\tname=" + getName() +
               "\n}";
    }
}
