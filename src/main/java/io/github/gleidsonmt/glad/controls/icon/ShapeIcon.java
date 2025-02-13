package io.github.gleidsonmt.glad.controls.icon;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/10/2024
 */
public class ShapeIcon extends Label {

    private SVGPath shape;
    private final DoubleProperty size = new SimpleDoubleProperty(20);
    private final SimpleObjectProperty<Icon> icon = new SimpleObjectProperty<>();

    public ShapeIcon() {
        this(Icon.NONE);
    }

    public ShapeIcon(Icon icon) {
        init(icon);
        this.icon.set(icon);
        getStyleClass().add("icon-shape");
//        setSnapToPixel(true);

        this.icon.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setShape(createShape(newValue));
            }
        });

//        this.maxHeightProperty().bindBidirectional(size);
//        this.maxWidthProperty().bindBidirectional(size);
        this.prefHeightProperty().bindBidirectional(size);
        this.prefWidthProperty().bindBidirectional(size);
//        setMinSize(size.get(), size.get());
    }

    private void init(Icon icon){
        shape = createShape(icon);
        setShape(shape);
    }

    private SVGPath createShape(Icon icon) {
        shape = new SVGPath();
        shape.setStrokeWidth(2);
        shape.setContent(icon.getContent());
        this.getStyleClass().add(icon.name().toLowerCase());
        return shape;
    }

    public void setSize(double size){
        this.size.set(size);
    }

    public Icon getIcon() {
        return icon.get();
    }

    public SimpleObjectProperty<Icon> iconProperty() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon.set(icon);
    }
}
