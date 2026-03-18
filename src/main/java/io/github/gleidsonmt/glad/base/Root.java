package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.responsive.AbstractContainer;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class Root extends AbstractContainer<DefaultBreak> {

    private final Behavior behavior;
    private final Flow flow;
    private  Layout layout;

    /**
     * Initializes layout, flow, behavior; adds listeners for dynamic adjustments
     */
    public Root(Layout layout) {
        this.flow = new FlowImpl(this);
        this.behavior = new BehaviorImpl(this);
        setLayout(layout);

        // removes any node with absolute position, like alerts, dialogs, etc.
        widthProperty().addListener((_, _, _) -> flow.clear());

        sceneProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                newValue.addEventFilter(ExecutionEventError.ACTION_ERROR, _ -> {
                    // testing future implementations
                });
            }
        });
    }

    public void setLayout(Layout layout) {
        this.getChildren().setAll((Node) layout);
        this.layout = layout;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public Flow flow() {
        return this.flow;
    }

    public Behavior behavior() {
        return this.behavior;
    }
}