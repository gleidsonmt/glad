package io.github.gleidsonmt.glad.base.responsive.sizer;

import io.github.gleidsonmt.glad.base.responsive.Breaker;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

/**
 * This class acts like an observer for size/width changes on the panel.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
public class Sizer<T extends Size> {

    private final ObjectProperty<T> size = new SimpleObjectProperty<>();
    private final T[] sizes;
    private final BooleanProperty log = new SimpleBooleanProperty();
    private final Breaker<T> breaker;

    /**
     * @param region The region that width is observed.
     * @param sizes  The sizes to update the view when observed is in position.
     */
    public Sizer(Region region, T[] sizes) {
        this(region, sizes, false);
    }

    public Sizer(Region region, T[] sizes, boolean _log) {
        this(region, sizes, new Breaker<>(), _log);
    }

    public Sizer(Region region, T[] sizes, Breaker<T> breaker) {
        this(region, sizes, breaker, false);
    }

    /**
     * @param region The region that width is observed.
     * @param sizes  The sizes to update the view when observed is in position.
     * @param _log   With it need to logs out.
     */
    public Sizer(Region region, T[] sizes, Breaker<T> breaker, boolean _log) {
        this.log.set(_log);
        this.breaker = breaker;
        this.sizes = sizes;
        region.sceneProperty().addListener((_, oldValue, newValue) -> {
            // Updates size listener on scene width changes
            if (newValue != null) {
                doAction(newValue.getWidth());
                newValue.widthProperty().addListener(updateSizeListener);
            } else {
                if (oldValue != null) oldValue.widthProperty().removeListener(updateSizeListener);
            }
        });
    }

    /**
     * Clears scene width listener from a region
     */
    public void clear(Region re) {
        if (re.getScene() != null) {
            re.getScene().widthProperty().removeListener(updateSizeListener);
        }
        re.sceneProperty().addListener((_, _, newValue) -> newValue.widthProperty().removeListener(updateSizeListener));
    }

    /**
     * Returns size based on input width
     */
    public T getSize(double width) {
        if (width >= this.sizes[this.sizes.length - 1].getMax()) {
            return this.sizes[this.sizes.length - 1];
        }

        if (width <= this.sizes[0].getMax()) {
            return this.sizes[0];
        }

        // Iterates sizes; returns size when width is within range
        for (int i = 0; i < this.sizes.length - 1; i++) {
            if (width >= this.sizes[i].getMax() && !(width >= this.sizes[i + 1].getMax())) {
                return this.sizes[i];
            }
        }
        return null;
    }

    private final ChangeListener<Number> updateSizeListener = (_, _, newValue) -> doAction(newValue.doubleValue());

    private void doAction(double width) {
        T act = getSize(width);
        if (size.get() == null || size.get() != act) {
            size.set(act);
            if (this.log.get()) {
                System.out.println("[ <= " + act.getMax() + "] -> " + act);
            }
            change(act);
        }
    }

    public void setLog(boolean log) {
        this.log.set(log);
    }

    @ApiStatus.Experimental
    public void change(T t) {
        this.breaker.doAction(t);
    }

    public Breaker<T> getBreaker() {
        return breaker;
    }

    public T[] getSizes() {
        return sizes;
    }
}
