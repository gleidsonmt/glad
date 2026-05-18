

package io.github.gleidsonmt.glad.base.responsive;

import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  26/09/2024
 */
@SuppressWarnings("unused")
public final class BreakPoint {

    private final List<Size> listBreaks;
    private final EventHandler<ActionEvent> eventHandler;

    public BreakPoint(EventHandler<ActionEvent> eventHandler, Size _break) {
        this(eventHandler, new Size[]{_break});
    }

    public BreakPoint(EventHandler<ActionEvent> eventHandler, Size... breaks) {
        this.listBreaks = List.of(breaks);
        this.eventHandler = eventHandler;
    }

    public List<Size> getBreaks() {
        return listBreaks;
    }

    public EventHandler<ActionEvent> getEventHandler() {
        return eventHandler;
    }

    @Override
    public String toString() {
        return "BreakPoint{" + "points=" + listBreaks +
               "}";
    }
}