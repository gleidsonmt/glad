package io.github.gleidsonmt.glad.responsive;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/09/2024
 */
@SuppressWarnings("unused")
public final class BreakPoint {

    private final List<Break> listBreaks;
    private final EventHandler<ActionEvent> eventHandler;

    @Contract(pure = true)
    public BreakPoint(EventHandler<ActionEvent> eventHandler, Break _break) {
        this(eventHandler, new Break[]{_break});
    }

    @Contract(pure = true)
    public BreakPoint(EventHandler<ActionEvent> eventHandler, Break... breaks ) {
        this.listBreaks = List.of(breaks);
        this.eventHandler = eventHandler;
    }

    @Contract(pure = true)
    public List<Break> getBreaks() {
        return listBreaks;
    }

    @Contract(pure = true)
    public EventHandler<ActionEvent> getEventHandler() {
        return eventHandler;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "BreakPoint{" + "\n\tbreaks=" + listBreaks +
               "\n}";
    }
}
