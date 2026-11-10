package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.Foreground;
import io.github.gleidsonmt.glad.base.internal.ForegroundImpl;
import io.github.gleidsonmt.glad.base.responsive.AbstractContainer;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  26/01/2025
 */
public class Root extends AbstractContainer<DefaultBreak> {

    private final Behavior behavior;
    private final Flow flow;

    private final Foreground foreground;

    private final ObjectProperty<Node> content = new SimpleObjectProperty<>();

    private boolean block = false;

    /**
     * Initializes layout, flow, behavior; adds listeners for dynamic adjustments
     */
    public Root(Node layout) {

        // container/root/this 6
        // foreground 5
        // drawer 4
        // flow 3
        // dialogs 2
        // alerts 1

        this.flow = new FlowImpl(this);
        this.behavior = new BehaviorImpl(this);
        this.foreground = new ForegroundImpl(this);

        setContent(layout);
        // removes any node with absolute position, like alerts, dialogs, etc.
        widthProperty().addListener((_, _, _) -> {
//            System.out.println("isBlocked() = " + isBlocked());
            if (isBlocked()) return;
//            flow.clear();
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
        this.content.set(content);
        content.setViewOrder(6);
        super.getChildren().setAll(content);
    }

    public Node getContent() {
        return content.get();
    }

    public ObjectProperty<Node> contentProperty() {
        return content;
    }

    public Foreground getForeground() {
        return foreground;
    }

    public void block() {
        this.block = true;
//        if (!isBlocked()) super.getChildren().add((Node) foreground);
//        foreground.show();
    }

    public void unblock() {
        this.block = false;
        foreground.restyle(WrapperEffect.NONE, 7);

        if (behavior.drawer() != null) behavior.drawer().hide();
        if (behavior.alert() != null) behavior.alert().hide();
        if (behavior.dialog() != null) behavior.dialog().hide();
//        if (!isBlocked()) return;
//        getChildren().remove((Node) foreground);
    }

    public boolean isBlocked() {
        return this.block;
//        return super.getChildren().contains((Node) foreground);
    }
}