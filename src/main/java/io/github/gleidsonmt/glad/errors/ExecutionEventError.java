package io.github.gleidsonmt.glad.errors;

import javafx.event.Event;
import javafx.event.EventType;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/05/2025
 */
@ApiStatus.Experimental
public class ExecutionEventError extends Event {

    private String additionMessage;
    private Throwable error;

    public static final EventType<ExecutionEventError> ANY = new EventType<>(Event.ANY, "ANY");
    public static final EventType<ExecutionEventError> ACTION_ERROR = new EventType<>(ANY, "ACTION_ERROR");

    public ExecutionEventError(Throwable error) {
        super(ACTION_ERROR);
        this.error = error;
    }

    public ExecutionEventError(Throwable error, String additionMessage) {
        super(ACTION_ERROR);
        this.error = error;
        this.additionMessage = additionMessage;
    }

    public ExecutionEventError(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public String getAdditionMessage() {
        return additionMessage;
    }

    public Throwable getError() {
        return error;
    }


}
