package io.github.gleidsonmt.glad.controls.avatar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/04/2024
 */
public class AvatarView extends Rectangle {

    private final ObjectProperty<Image> image =  new SimpleObjectProperty<>();

    public AvatarView() {
        this(null);
    }

    public AvatarView(Image image) {
        this(image, 40);
    }

    public AvatarView(Image image, double radius) {
        this(image, radius, radius);
    }

    public AvatarView(Image image, double radius, double size) {
        this(image, radius, size, size);
    }

    public AvatarView(Image image,  double arc, double width, double height) {
        getStyleClass().add("avatar-view");
        if (image != null)
            this.setFill(new ImagePattern(image));

//        setRadius(40);
        setWidth(width);
        setHeight(height);

        setArcWidth(arc);
        setArcHeight(arc);

        this.image.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFill(new ImagePattern(newValue));
            }
        });

    }

    public void setSize(double s) {
        setWidth(s);
        setHeight(s);
    }

    public void setRadius(double s) {
        setArcWidth(s);
        setArcHeight(s);
    }

    public Image getImage() {
        return image.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }


}
