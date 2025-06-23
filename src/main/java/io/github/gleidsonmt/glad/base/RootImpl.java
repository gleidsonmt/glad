package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.errors.ExecutionEventError;
import io.github.gleidsonmt.glad.base.internal.BehaviorImpl;
import io.github.gleidsonmt.glad.base.internal.FlowImpl;
import io.github.gleidsonmt.glad.base.internal.WrapperImpl;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class RootImpl extends Container implements Root {

    private BehaviorImpl behavior;
    private final Flow flow;
    private final Wrapper wrapper;

    // Breakpoint, use to change the layout to phone or bigger
    private final DoubleProperty breakpoint = new SimpleDoubleProperty(640);
    private LayoutImpl layout;
    private LayoutImpl oldLayout;

    public RootImpl(LayoutImpl layout) {
        super();
        this.layout = layout;
        this.flow = new FlowImpl(this);
        this.wrapper = new WrapperImpl(this);
        this.behavior = new BehaviorImpl(this, layout);
        this.getChildren().add(layout);

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

    public void setLayout(Layout _layout) {
        this.oldLayout = this.layout;
        this.getChildren().removeAll(layout);
        this.layout = (LayoutImpl) _layout;
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
}