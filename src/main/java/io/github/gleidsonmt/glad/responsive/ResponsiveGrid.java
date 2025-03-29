package io.github.gleidsonmt.glad.responsive;

import io.github.gleidsonmt.glad.responsive.sizer.Sizer;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
@ApiStatus.Internal
@ApiStatus.Experimental
@Deprecated
public class ResponsiveGrid extends GridPane implements Actionable<Break> {

    private final List<BreakPoint> points = new ArrayList<>();

    public ResponsiveGrid() {
        this(false);
    }

    public ResponsiveGrid(boolean log) {
        new Sizer<>(this, Break.values()) {
            @Override
            public void change(Break aBreak) {
                if (log) {
                    System.out.println("[" + aBreak + "] = " + aBreak.getMax());
                }
                doAction(aBreak);
            }
        };
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
     * @param event  The event to occurs.
     * @param breaks The breaks/size to event occurs.
     */
    @Deprecated
    public void addPoint(PointEvent event, Break... breaks) {
//        if (breaks.length == 0) this.points.add(new BreakPoint(event, Break.ALL));
//        else
            this.points.add(new BreakPoint(event, breaks));
    }

    @Deprecated
    public void addPoints(BreakPoint... points) {
        this.points.addAll(Arrays.stream(points).toList());
    }

}
