package io.github.gleidsonmt.glad.controls.avatar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/04/2024
 */
@Deprecated
public class AvatarCircleView extends Circle {

    private final ObjectProperty<Image> image =  new SimpleObjectProperty<>();

    public AvatarCircleView() {
        this(null);
    }

    public AvatarCircleView(Image image) {
        this(image, 20);
    }

    public AvatarCircleView(Image image, double radius) {
        getStyleClass().add("avatar-circle-view");
        if (image != null)
            this.setFill(new ImagePattern(image));

        setRadius(radius);

        this.image.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFill(new ImagePattern(newValue));
            }
        });

    }

    public Image getImage() {
        return image.get();
    }

    public void setImage(Image image) {
        this.image.set(image);
    }

}
