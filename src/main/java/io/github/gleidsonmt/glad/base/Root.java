

package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.Foreground;
import io.github.gleidsonmt.glad.base.internal.ForegroundImpl;
import io.github.gleidsonmt.glad.base.responsive.AbstractContainer;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  26/01/2025
 */
public class Root extends AbstractContainer<DefaultBreak> {

    private final Behavior behavior;
    private final Flow flow;
    private Layout layout;

    private Foreground foreground;

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

    @Deprecated(forRemoval = true)
    public void setLayout(Layout layout) {
        this.getChildren().setAll((Node) layout);
        this.layout = layout;
    }

    @Deprecated(forRemoval = true)
    public Layout getLayout() {
        return this.layout;
    }

    public Flow flow() {
        return this.flow;
    }

    public Behavior behavior() {
        return this.behavior;
    }


//    @Override
//    public ObservableList<Node> getChildren() {
//        return FXCollections.unmodifiableObservableList(super.getChildren());
//    }

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
        super.getChildren().remove(foreground);
    }

    public boolean isBlocked() {
        return super.getChildren().contains(foreground);
    }
}