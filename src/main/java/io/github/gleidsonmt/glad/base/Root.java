package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.dialog.Wrapper;
import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.WrapperImpl;
import io.github.gleidsonmt.glad.base.responsive.AbstractContainer;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class Root extends AbstractContainer<DefaultBreak> {

    private final Behavior behavior;
    private final Flow flow;
    private final Wrapper wrapper;

    // Breakpoint, use to change the layout to phone or bigger
    private final DoubleProperty breakpoint = new SimpleDoubleProperty(640);

    private final Layout layout;

    public Root(Layout layout) {
        this.flow = new FlowImpl(this);
        this.wrapper = new WrapperImpl(this);
        this.behavior = new BehaviorImpl(this);
        this.getChildren().add((Node) layout);

        this.layout = layout;

        widthProperty().addListener((_, _, _) -> {
//            wrapper.hide();
            flow.clear();
        });

        sceneProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                newValue.addEventFilter(ExecutionEventError.ACTION_ERROR, e -> {
                    // testing
                });
            }
        });
    }

    public Layout getLayout() {
        return this.layout;
    }

    public Flow flow() {
        return this.flow;
    }

    @Deprecated(forRemoval = true)
    public Wrapper wrapper() {
        return this.wrapper;
    }

    public Behavior behavior() {
        return this.behavior;
    }
}