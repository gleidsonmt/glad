package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.internal.AlertImpl;
import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.WrapperImpl;
import io.github.gleidsonmt.glad.dialog.Dialog;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class Root extends StackPane {

    private BehaviorImpl behavior;
    private final Flow flow;
    private final Wrapper wrapper;

    // Breakpoint, use to change the layout to phone or bigger
    private final DoubleProperty breakpoint = new SimpleDoubleProperty(640);
    private Layout layout;
    private Layout oldLayout;

    public Root(Layout layout) {
        this.layout = layout;
        this.flow = new FlowImpl(this);
        this.wrapper = new WrapperImpl(this);
        this.behavior = new BehaviorImpl(this, layout);
        this.getChildren().add(layout);

       widthProperty().addListener((_, _, _) -> {
           wrapper.hide();
           flow.clear();
       });
    }

    public void setLayout(Layout _layout) {
        this.oldLayout = this.layout;
        this.getChildren().removeAll(layout);
        this.layout = _layout;
        if (!this.getChildren().isEmpty()) {
            this.getChildren().set(0, layout);
        } else {
            this.getChildren().add(layout);
        }

    }

    public Layout getLayout() {
        return (Layout) this.layout;
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

    /**
     * Get in which width the view will change to phone size.
     * @return The width.
     */
    @Deprecated(forRemoval = true)
    public double getBreakpoint() {
        return breakpoint.get();
    }

    /**
     * Set in which width the view will change to phone size.
     */
    @Deprecated(forRemoval = true)
    public void setBreakpoint(double breakpoint) {
        this.breakpoint.set(breakpoint);
    }

    @Deprecated(forRemoval = true)
    public DoubleProperty breakpointProperty() {
        return this.breakpoint;
    }


}
