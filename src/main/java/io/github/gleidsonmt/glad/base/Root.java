package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.Foreground;
import io.github.gleidsonmt.glad.base.internal.ForegroundImpl;
import io.github.gleidsonmt.glad.base.responsive.AbstractContainer;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  26/01/2025
 */
public class Root extends AbstractContainer<DefaultBreak> {

    private final Behavior behavior;
    private final Flow flow;

    private final Foreground foreground;

    private Node content;
    /**
     * Initializes layout, flow, behavior; adds listeners for dynamic adjustments
     */
    public Root(Node layout) {
        this.flow = new FlowImpl(this);
        this.behavior = new BehaviorImpl(this);
        this.foreground = new ForegroundImpl(this);

        setContent(layout);
        // removes any node with absolute position, like alerts, dialogs, etc.
        widthProperty().addListener((_, _, _) -> {
            if (isBlocked()) return;
            flow.clear();
        });

        sceneProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                newValue.addEventFilter(ExecutionEventError.ACTION_ERROR, _ -> {
                    // testing future implementations
                });
            }
        });
    }

    public Flow flow() {
        return this.flow;
    }

    public Behavior behavior() {
        return this.behavior;
    }

    public void setContent(Node content) {
        this.content = content;
        super.getChildren().setAll(content);
    }

    public Node getContent() {
        return content;
    }

    public Foreground getForeground() {
        return foreground;
    }

    public void block() {
        if (!isBlocked()) super.getChildren().add((Node) foreground);
        foreground.show();
    }

    public void unblock() {
        if (!isBlocked()) return;
        getChildren().remove((Node) foreground);
    }

    public boolean isBlocked() {
        return super.getChildren().contains((Node) foreground);
    }
}