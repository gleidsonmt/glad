package io.github.gleidsonmt.glad.base.responsive;


import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/// The class created for using a model to the main container.
///
/// @author Gleidson Neves da Silveira | [gleidisonmt@gmail.com](mailto:gleidisonmt@gmail.com)
/// Create on  19/03/2025
@SuppressWarnings("unused")
public class AbstractContainer<T extends Size> extends StackPane {

    //    Responsible for responsive actions.
    protected Sizer<T> sizer;

    @SuppressWarnings("unchecked")
    public AbstractContainer() {
        this((T[]) DefaultBreak.values());
    }

    public AbstractContainer(T[] values) {
        setSizer(new Sizer<>(this, values));
    }

    public List<BreakPoint> getBreakpoints() {
        return this.sizer.getBreaker().getPoints();
    }

    /**
     * Instead of getting using {@link #getBreakpoints()} you can pass an event and a size directly.
     *
     * @param event The event to occur.
     * @param sizes The sizes when this event occurs.
     */
    @SafeVarargs
    public final void addBreakpoint(EventHandler<ActionEvent> event, T... sizes) {
        this.sizer.getBreaker().getPoints().add(new BreakPoint(event, sizes));
    }

    /**
     * Add breakpoints using strings.
     * Pass your action and a set of strings that you will be converted in sizes.
     * A breakpoint will be added with those strings.
     * <pre>
     * {@code
     * container.addBreakpoint(_ -> {
     * title.getStyleClass().remove("bg-red-500");
     * }, "LG MD");
     * }
     * </pre>
     * The MD and MD examples will be converted to an enum with the same name.
     * Additionally, you can add an extra operand symbol.
     * <pre>
     * {@code
     * container.addBreakpoint(_ -> {
     * title.getStyleClass().remove("bg-red-500");
     * }, "<LG >MD");
     * }
     * </pre>
     * The symbol > means you want to add every breakpoint that is greater than MD or
     * < means you want to add every breakpoint that is less than LG.
     *
     * @param event The event to occur.
     * @param sizes The sizes when this event occurs.
     */
    public void addBreakpoint(EventHandler<ActionEvent> event, String sizes) {
        List<T> list = null;
        // Filters sizes by name and collects matches
        for (String size : sizes.split(" ")) {
            if (size.contains("<") || size.contains(">")) {
                list = getByOperation(size, size.substring(0, 1));
            } else {
                // Filters sizes by name and collects matches
                list = Arrays.stream(this.sizer.getSizes()).filter(el -> el.name().equalsIgnoreCase(size)).collect(Collectors.toList());
            }
        }
        // if found a size, add a point.
        if (list != null) {
            for (T size : list) {
                addBreakpoint(event, size);
            }
        }
    }

    // Iterate and find the correct operation.
    private @Nullable @Unmodifiable List<T> getByOperation(String size, String operation) {
        var temp = size.replaceAll("[^a-zA-Z]", "");
        // Normalizes size string; filters sizes by maximum
        Optional<T> element = filterByName(temp);
        if (element.isPresent()) {
            var comp = element.get();
            return Arrays.stream(this.sizer.getSizes())
                    .filter(el -> operation.equals(">") ? el.getMax() > comp.getMax() : el.getMax() < comp.getMax())
                    .toList();
        }
        return null;
    }

    private @NotNull Optional<T> filterByName(String name) {
        return Arrays.stream(this.sizer.getSizes()).filter(el -> el.name().equalsIgnoreCase(name))
                .findAny();
    }

    public void addBreakpoint(EventHandler<ActionEvent> event, BreakPoint size) {
        getBreakpoints().add(size);
    }

    public void addBreakpoint(EventHandler<ActionEvent> event, BreakPoint... sizes) {
        getBreakpoints().addAll(List.of(sizes));
    }

    @ApiStatus.Experimental
    public void update() {
//        breaker.doAction(this.sizer.getSize(this.getWidth()));
        this.sizer.change(this.sizer.getSize(this.getWidth()));
    }

    protected void setSizer(Sizer<T> sizer) {
        if (this.sizer != null) this.sizer.clear(this);
        this.sizer = sizer;
    }

    /**
     * See the values and the width relation.
     * [ < number] - Size
     */
    public void log() {
        this.sizer.setLog(true);
    }
}