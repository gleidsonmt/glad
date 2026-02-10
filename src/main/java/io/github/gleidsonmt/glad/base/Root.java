package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.dialog.Wrapper;
import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.WrapperImpl;
import io.github.gleidsonmt.glad.base.responsive.AbstractContainer;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.base.responsive.BreakPoint;
import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Arrays;

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
            wrapper.hide();
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

    /**
     * Get in which width the view will change to phone size.
     *
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

    @Deprecated(forRemoval = true)
    public void addPoint(EventHandler<ActionEvent> event, Size... breaks) {
//        breaker.getPoints().add(new BreakPoint(event, breaks));
    }

    @Deprecated(forRemoval = true)
    public void addPoints(BreakPoint... points) {
//        breaker.getPoints().addAll(Arrays.stream(points).toList());
    }
    @Deprecated(forRemoval = true)
    public void clearPoints() {
//        breaker.getPoints().clear();
    }
}