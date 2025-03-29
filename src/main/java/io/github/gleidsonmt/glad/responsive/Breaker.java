package io.github.gleidsonmt.glad.responsive;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/03/2025
 */
public interface Breaker extends Actionable<Break> {

    List<BreakPoint> points = new ArrayList<>();

    @Override
    default void doAction(Break size) {
        BreakPoint br = points.stream().filter(p -> p.getBreaks().contains(size)).findAny().orElse(null);

        if (br == null) {
            return;
        }
        // Make an action defined by user.
        // This action is convenient to be a new layout based on the scene size
        br.getEventHandler().handle(new ActionEvent());
    }

    /**
     * Add a point to make an action when the scene size is equal this break point.
     * @param event  The event to occur.
     * @param breaks The breaks/size to event occurs.
     */
    default void addPoint(EventHandler<ActionEvent> event, Break... breaks) {
        Breaker.points.add(new BreakPoint(event, breaks));
    }

    /**
     * Add a multiple points to make an action when the scene size is equal this break point.
     * @param points The Breakpoints.
     */
    default void addPoints(BreakPoint... points) {
        Breaker.points.addAll(Arrays.stream(points).toList());
    }
}
