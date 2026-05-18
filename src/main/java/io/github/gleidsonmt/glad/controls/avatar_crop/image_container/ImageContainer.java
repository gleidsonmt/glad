

package io.github.gleidsonmt.glad.controls.avatar_crop.image_container;

import io.github.gleidsonmt.glad.controls.avatar_crop.BorderClip;
import io.github.gleidsonmt.glad.controls.avatar_crop.shapes.BorderCircle;
import io.github.gleidsonmt.glad.controls.avatar_crop.shapes.CircleView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  14/11/2024
 */
public class ImageContainer extends StackPane {

    private ScrollView scrollView;

    private StackPane one = new StackPane();

    private Rectangle rectangle;

    private ObjectProperty<BorderClip> borderClip = new SimpleObjectProperty<>(BorderClip.CIRCLE);

    public ImageContainer(Image image) {
        scrollView = new ScrollView(image);
        configCircleView();

        borderClipProperty().addListener((observableValue, borderClip, newValue) -> {
            if (!newValue.equals(BorderClip.RECT)) {
                configCircleView();
            } else {
                configRectView();
            }
        });

        double max = 500;
        this.setMaxWidth(max);
        this.setMaxHeight(max);
        this.setMinWidth(max);
        this.setMinHeight(max);

//        scrollView.setFitToWidth(true);
//        scrollView.setFitToHeight(true);

        one.setStyle("-fx-background-color: #c0c7ff;");
    }

    private void configCircleView() {
        BorderCircle borderCircle = new BorderCircle(250);
        borderCircle.setRadius(250);
        CircleView circleView = new CircleView(250);
        circleView.setRadius(240);
        CircleView dois = new CircleView(240);
        dois.layoutXProperty().bind(circleView.layoutXProperty());
        dois.layoutYProperty().bind(circleView.layoutYProperty());
        this.getChildren().setAll(one, scrollView, circleView);
        this.scrollView.setClip(dois);
    }

    private void configRectView() {
        this.getChildren().setAll(one, scrollView);
        this.scrollView.setClip(null);
    }

    public Image getImage() {
        return scrollView.getScrollViewContent().getImage();
    }

    public ScrollView getScrollView() {
        return scrollView;
    }

    public BorderClip getBorderClip() {
        return borderClip.get();
    }

    public ObjectProperty<BorderClip> borderClipProperty() {
        return borderClip;
    }

    public void setBorderClip(BorderClip borderClip) {
        this.borderClip.set(borderClip);
    }
}
