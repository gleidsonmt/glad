package io.github.gleidsonmt.glad.responsive_grid.sizer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
public abstract class Sizer<T extends Size> {

    private final ObjectProperty<T> size = new SimpleObjectProperty<>();
    private final T[] sizes;
    private final BooleanProperty log = new SimpleBooleanProperty();

    public Sizer(Region region, T[] sizes) {
        this(region, sizes, false);
    }

    public Sizer(Region region, T[] sizes, boolean _log) {
        this.log.set(_log);
        this.sizes = sizes;
        region.sceneProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.widthProperty().addListener(updateSizeListener);
            } else {
                if (oldValue != null) oldValue.widthProperty().removeListener(updateSizeListener);
            }
        });
    }

    private T getSize(double width) {
        if (width >= this.sizes[this.sizes.length -1].getMax() ) {
            return this.sizes[this.sizes.length -1];
        }

        if (width <= this.sizes[0].getMax()) {
            return this.sizes[0];
        }
        for (int i = 0; i < this.sizes.length-1;i++) {
            if (width >= this.sizes[i].getMax() && !(width >= this.sizes[i +1].getMax())) {
                return this.sizes[i];
            }

        }
        return null;
    }

    private final ChangeListener<Number> updateSizeListener = (observableValue, oldValue, newValue) -> {
        T act = getSize(newValue.doubleValue());
        if (size.get() == null || size.get() != act) {
            size.set(act);
            change(act);
            if (log.get()) {
                assert act != null;
                System.out.println("[ < " + act.getMax() + "] - " + act);
            }
        }
    };

    public abstract void change(T t);

}
