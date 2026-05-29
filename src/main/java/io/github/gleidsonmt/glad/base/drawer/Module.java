package io.github.gleidsonmt.glad.base.drawer;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  10/06/2025
 */
@ApiStatus.Experimental
public abstract class Module {

    private final String name;
    protected Node graphic;
    protected Node content;
    protected Node node;

    private Module parent;
    private Pane container;

    protected Module(String name) {
        this.name = name;
    }

    protected Module(String name, Node graphic) {
        this.name = name;
        this.graphic = graphic;
    }

    public void setContainer(Pane container) {
        this.container = container;
    }

    public Pane getContainer() {
        return container;
    }

    public String getName() {
        return name;
    }

    public Node getGraphic() {
        return graphic;
    }

    public Node getContent() {
        return content;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public void setContent(Node content) {
        this.content = content;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Return the visual instead of the content of the view.
     * @return The visual node.
     */
    public Node getNode() {
        return node;
    }

//    @ApiStatus.Experimental
//    default void onExit(Layout layout) {
//    }
//
//    @ApiStatus.Experimental
//    default void onEnter(Layout layout) {
//    }

    @Override
    public String toString() {
        return "{\"Module\":{"
               + "\"container\":" + container
               + ", \"name\":\"" + name + "\""
               + ", \"graphic\":" + graphic
               + ", \"content\":" + content
               + ", \"node\":" + node
               + ", \"parent\":" + parent
               + "}}";
    }
}
