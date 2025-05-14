package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.responsive.Actionable;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.base.responsive.BreakPoint;
import io.github.gleidsonmt.glad.base.responsive.PointEvent;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
public class Layout extends BorderPane implements LayoutActions, Actionable<Break> {

    private final List<BreakPoint> points = new ArrayList<>();
    private final Sizer<Break> sizer;

    public Layout() {
        this(null);
    }

    public Layout(Node node) {
        updateView(node);
        sizer = new Sizer<>(this, Break.values()) {
            @Override
            public void change(Break aBreak) {
                doAction(aBreak);
            }
        };

    }

    public Break getSize() {
        return sizer.getSize(getWidth());
    }

    public boolean biggerThan(Break first, Break second) {
        return first.getMax() > second.getMax();
    }

    public boolean lowerThan(Break first, Break second) {
        return first.getMax() < second.getMax();
    }

    @Override
    public void updateView(Node view) {
        setCenter(view);
    }

    @Override
    public void doAction(Break breaks) {
        BreakPoint br = points.stream().filter(p -> p.getBreaks().contains(breaks)).findAny().orElse(null);

        if (br == null) {
            return;
        }
        // Make an action defined by user.
        // This action is convenient to be a new layout based on the scene size
        br.getEventHandler().handle(new ActionEvent());
    }

    /**
     * Add a point to make an action when the scene size is equal this break point.
     *
     * @param event  The event to occurs.
     * @param breaks The breaks/size to event occurs.
     */
    @Deprecated(forRemoval = true)
    public void addPoint(PointEvent event, Break... breaks) {
        this.points.add(new BreakPoint(event, breaks));
    }

    public void addPoints(BreakPoint... points) {
        this.points.addAll(Arrays.stream(points).toList());
    }
}
