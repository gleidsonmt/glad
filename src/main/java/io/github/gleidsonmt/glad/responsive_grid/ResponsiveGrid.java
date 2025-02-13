package io.github.gleidsonmt.glad.responsive_grid;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tries to solve the problem with responsive in apps.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/09/2024
 */
@ApiStatus.Internal
@ApiStatus.Experimental
@ApiStatus.AvailableSince("0.1.1")
public class ResponsiveGrid extends GridPane {

    private Break old = Break.MD;

    private final List<BreakPoint> points = new ArrayList<>();
    private boolean log = false;

    @Deprecated(forRemoval = true)
    public ResponsiveGrid(ReadOnlyDoubleProperty property) {
        try {
            setListener(property);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponsiveGrid() {
        this(true);
    }

    public ResponsiveGrid(boolean needs) {
        if (!needs) return; // if don't need responsive.. returns a single GridPane
        this.sceneProperty().addListener(this::changed); // add a listener
    }

    @Contract("null -> fail")
    private void setListener(ReadOnlyDoubleProperty widthProperty) {
        // Do the first action.. when scene appears.
        Break act = getSize(widthProperty.doubleValue());
        doAction(act);
        old = act;
        // prevent multi listener
        widthProperty.removeListener(handleSize);
        // add the listener to scene width
        widthProperty.addListener(handleSize);
    }

    private final ChangeListener<Number> handleSize = (observable, oldValue, newValue) -> {
        // Get the actual size of the width. Example.. 1200px returns Break.XXL..
        Break act = getSize(newValue.doubleValue());
        // To prevent every update when the size is update...
        // Verify if the new size is a new break value...
        // ex.: if the size 1200 updates to 1201, do not need to update layout
        // but if the size is 728 and updates to 1200.. so jumps from Break.MD to Break.XXL
        if (old == null || old != act) {
            old = act;

            if (log) { // if you need to log... to view in which size is a scene is
                System.out.println("Break: " + old);
            }
            doAction(act); // Make a action
        }
    };

    @SuppressWarnings("unused")
    public void log() {
        log = true;
    }

    private void doAction(Break breaks) {
        // find if the actual break points matches with the points..
        BreakPoint br = points.stream().filter(p -> p.getBreaks().contains(breaks)).findAny().orElse(null);
        // If br doesn't math with the points.. verify if has a default Break.ALL
        if (br == null) {
            points.stream().filter(p -> p.getBreaks().contains(Break.ALL)).findAny().ifPresent(s -> s.getEventHandler().handle(new ActionEvent()));
            return;
        }
        // Make an action defined by user..
        // This action is convenient to be a new layout based on the scene size
        br.getEventHandler().handle(new ActionEvent());
    }

    private Break getSize(double width) {
        if (width <= 640) return Break.SM;
        if (width > 640 && width <= 768) return Break.MD;
        if (width > 768 && width <= 1024) return Break.LG;
        if (width > 1024 && width <= 1280) return Break.XL;
        if (width > 1290 && width < 1536) return Break.XXL;
        return Break.WIDE;
    }

    @Deprecated(forRemoval = true)
    public void addBreak(PointEvent event, Break... breaks) {
        if (breaks.length == 0) this.points.add(new BreakPoint(event, Break.ALL));
        else this.points.add(new BreakPoint(event, breaks));
    }

    /**
     * Add a point to make an action when the scene size is equal this break point.
     * @param event  The event to occurs.
     * @param breaks The breaks/size to event occurs.
     */
    public void addPoint(PointEvent event, Break... breaks) {
        if (breaks.length == 0) this.points.add(new BreakPoint(event, Break.ALL));
        else this.points.add(new BreakPoint(event, breaks));
    }

    public void addPoints(BreakPoint... points) {
        this.points.addAll(Arrays.stream(points).toList());
    }

    // Adds a listener to scene width
    private void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
        // if scene property is changed..
        if (newValue != null) {
            setListener(newValue.widthProperty()); // add an observer to scene width
        }
    }
}
