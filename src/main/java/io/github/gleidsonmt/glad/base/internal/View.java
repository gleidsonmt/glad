package io.github.gleidsonmt.glad.base.internal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/07/2024
 */
@ApiStatus.Experimental
public class View extends ModuleView implements Module {

    protected Node content;
    protected EventHandler<ActionEvent> onEnter;
    protected EventHandler<ActionEvent> onExit;

    public View(String name) {
        this(name, (Node) null);
    }

    public View(String name, Node content) {
        this(name, content, null, null);
    }

    public View(String name, Node content, EventHandler<ActionEvent> onEnter ) {
        this(name, content, onEnter,null);
    }

    public View(String name, EventHandler<ActionEvent> onEnter ) {
        this(name, null, onEnter,null);
    }

    public View(String name, EventHandler<ActionEvent> onEnter, EventHandler<ActionEvent> onExit ) {
        this(name, null, onEnter,onExit);
    }

    public View(String name, Node content, EventHandler<ActionEvent> onEnter, EventHandler<ActionEvent> onExit ) {
        super(name);
        this.content = content;
        this.onEnter = onEnter;
        this.onExit = onExit;
    }

    public EventHandler<ActionEvent> getOnEnter() {
        return onEnter;
    }

    public void setOnEnter(EventHandler<ActionEvent> onEnter) {
        this.onEnter = onEnter;
    }

    public EventHandler<ActionEvent> getOnExit() {
        return onExit;
    }

    public void setOnExit(EventHandler<ActionEvent> onExit) {
        this.onExit = onExit;
    }

    public View(String name, Node content, ModuleView... children ) {
        super(name, children);
        this.content = content;
    }

    public void setContent(Node content) {
        this.content = content;
    }

    public Node getContent() {
        return content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("View{");
        sb.append("\n\tcontent=").append(content);
        sb.append("\n\tonEnter=").append(onEnter);
        sb.append("\n\tonExit=").append(onExit);
        sb.append("\n}");
        return sb.toString();
    }
}
