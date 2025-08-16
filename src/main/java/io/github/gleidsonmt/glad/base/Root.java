package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.base.responsive.BreakPoint;
import io.github.gleidsonmt.glad.base.responsive.Container;
import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.WrapperImpl;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class Root extends Container<Break> {

    private Behavior behavior;
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

//                    System.out.println("e.getSource() = " + e.getSource());
//                    System.out.println("e.getClass() = " + e.getClass());
//                    System.out.println("e.getTarget() = " + e.getTarget());
//                    System.out.println("e.getEventType() = " + e.getEventType());

                    Text text = new Text();
                    TextFlow flow = new TextFlow(text);
                    flow.setPadding(new Insets(10));
                    flow.getStyleClass().addAll("border-l-2", "border-danger");
                    flow.setStyle("-fx-background-color: derive(-danger, 115%); ");
                    text.setStyle("-fx-fill: -danger; -fx-font-weight: bold;");
//                    text.getStyleClass().addAll("font-instagram-headline");

                    ScrollPane container = new ScrollPane(flow);
                    container.setFitToWidth(true);
                    container.setFitToHeight(true);
                    container.setMaxWidth(300);
                    container.setMaxHeight(400);

//                    -fx-vbar-policy
                    container.setStyle("-fx-vbar-policy: always;");

                    /**
                     * Source: javafx.scene.Scene@446a4378,
                     * Class: io.github.gleidsonmt.glad.errors.ExecutionEventError
                     * Target: Button@3f5d6955[styleClass=button]'Test'
                     * EventType: ACTION_ERROR
                     * Error {
                     *
                     * }
                     *
                     */

                    StringBuilder builder = new StringBuilder();
                    builder.append("Cause: ").append(e.getError().getCause());
                    builder.append("\nMessage: ").append(e.getError().getMessage());
                    builder.append("\n").append(e.getAdditionMessage());
                    text.setText(builder.toString());

                    Button ok = new Button("Fechar");
                    ok.setOnAction(_ -> {
                        Platform.exit();
                    });

                    behavior()
                            .alert()
                            .open("Oops..", container, AlertType.ERROR, ok);

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



    public void addPoint(EventHandler<ActionEvent> event, Size... breaks) {
        breaker.getPoints().add(new BreakPoint(event, breaks));
    }

    public void addPoints(BreakPoint... points) {
        breaker.getPoints().addAll(Arrays.stream(points).toList());
    }

    public void clearPoints() {
        breaker.getPoints().clear();
    }
}