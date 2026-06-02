

package io.github.gleidsonmt.glad.base.responsive;

import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  23/06/2025
 */
@Deprecated(forRemoval = true)
public interface PontEvents<T extends Size> {

    /**
     * Add a point to make an action when the scene size is equal this break point.
     *
     * @param event  The event to occur.
     * @param breaks The breaks/size to event occurs.
     */
    void addPoint(EventHandler<ActionEvent> event, T... breaks);

    /**
     * Add a multiple points to make an action when the scene size is equal this break point.
     *
     * @param points The Breakpoints.
     */
    void addPoints(BreakPoint... points);

    void clearPoints();
}
