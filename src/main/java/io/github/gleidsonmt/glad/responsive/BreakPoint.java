package io.github.gleidsonmt.glad.responsive;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/09/2024
 */
public final class BreakPoint {

    private final List<Break> listBreaks;
    private final EventHandler<ActionEvent> eventHandler;

    public BreakPoint(EventHandler<ActionEvent> eventHandler, Break breaks ) {
        this.listBreaks = List.of(breaks);
        this.eventHandler = eventHandler;
    }

    public BreakPoint(EventHandler<ActionEvent> eventHandler, Break... breaks ) {
        this.listBreaks = List.of(breaks);
        this.eventHandler = eventHandler;
    }

    public List<Break> getBreaks() {
        return listBreaks;
    }

    public EventHandler<ActionEvent> getEventHandler() {
        return eventHandler;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BreakPoint{");
        sb.append("\n\tbreaks=").append(listBreaks);
        sb.append("\n}");
        return sb.toString();
    }
}
