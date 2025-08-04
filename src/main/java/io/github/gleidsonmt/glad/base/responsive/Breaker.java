package io.github.gleidsonmt.glad.base.responsive;

import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/03/2025
 */
public class Breaker<T extends Size> implements Actionable<T> {

    private final List<BreakPoint> points = new ArrayList<>();

    @Override
    public void doAction(T size) {
        Platform.runLater(() -> {
            List<BreakPoint> p = points.stream().filter(el -> el.getBreaks().contains(size)).toList();
            if (p.isEmpty()) return;
            // Make an action defined by user.
            // This action is convenient to be a new layout based on the scene size
            p.forEach(e -> e.getEventHandler().handle(new ActionEvent()));
        });

    }

    public List<BreakPoint> getPoints() {
        return points;
    }

}
