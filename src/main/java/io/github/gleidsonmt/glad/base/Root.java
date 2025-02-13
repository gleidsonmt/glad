package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.WrapperImpl;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class Root extends StackPane {

    private final Behavior behavior;
    private final Flow flow;
    private final Wrapper wrapper;

    public Root(Container container) {
        this.flow = new FlowImpl(this);
        this.wrapper = new WrapperImpl(this);
        this.behavior = new BehaviorImpl(this, container);
        this.getChildren().add(container);
    }

    public Flow flow() {
        return this.flow;
    }

    public Wrapper wrapper() {
        return this.wrapper;
    }

    public Behavior behavior() {
        return this.behavior;
    }
}
