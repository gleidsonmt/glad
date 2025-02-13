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
public class AvatarRectView extends Rectangle {

    private final ObjectProperty<Image> image =  new SimpleObjectProperty<>();

    public AvatarRectView() {
        this(null);
    }

    public AvatarRectView(Image image) {
        getStyleClass().add("avatar-circle-view");
        if (image != null)
            this.setFill(new ImagePattern(image));

//        setRadius(40);
        setWidth(40);
        setHeight(40);

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
