package io.github.gleidsonmt.glad.controls.carousel;

import io.github.gleidsonmt.glad.Resources;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Skin;

/**
 * Carousel it's a region that can slide nodes with indicators and arrows to move through the children.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/08/2025
 */
public class Carousel<T extends Node> extends CarouselBase {

    private static final String DEFAULT_STYLE_CLASS = "carousel";
    private ObjectProperty<ObservableList<T>> items;
    private final BooleanProperty arrows = new SimpleBooleanProperty(this, "arrows", true);

    public Carousel(){
        this(FXCollections.observableArrayList());
    }

    @SafeVarargs
    public Carousel(T... items){
        this(FXCollections.observableArrayList(items));
    }

    public Carousel(ObservableList<T> items) {
        initialize();
        setAccessibleRole(AccessibleRole.NODE);
        setItems(items);
        getStylesheets().add(Resources.getCss("carousel.css"));
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CarouselSkin<>(this);
    }

    private void initialize(){
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    public final ObservableList<T> getItems() {
        return items == null ? null : items.get();
    }

    public void setItems(ObservableList<T> items) {
        this.itemsProperty().set(items);
    }

    public final ObjectProperty<ObservableList<T>> itemsProperty() {
        if (items == null) {
            items = new SimpleObjectProperty<>(this, "items");
        }
        return items;
    }

    /**
     * When the arrows are visible.
     * @return If the arrow are visible.
     */
    public boolean isArrows() {
        return arrows.get();
    }

    public BooleanProperty arrowsProperty() {
        return arrows;
    }

    /**
     * Turn the arrows visible or not.
     */
    public void setArrows(boolean arrows) {
        this.arrows.set(arrows);
    }
}
