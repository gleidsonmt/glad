package io.github.gleidsonmt.glad.base.responsive;

import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
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
public class ResponsiveGrid extends GridPane implements Actionable<Break>, Breaker {


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

}
