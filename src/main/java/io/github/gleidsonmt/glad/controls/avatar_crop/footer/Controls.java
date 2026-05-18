

package io.github.gleidsonmt.glad.controls.avatar_crop.footer;

import io.github.gleidsonmt.glad.controls.avatar_crop.custom_slider.CustomSlider;
import io.github.gleidsonmt.glad.controls.avatar_crop.image_container.AvatarCropImageView;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  17/11/2024
 */
public class Controls extends HBox {

    public Controls(AvatarCropImageView cropImageView) {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        SVGIcon first = new SVGIcon(Icon.IMAGE_FILL);
        SVGIcon second = new SVGIcon(Icon.IMAGE_FILL, 2);
        CustomSlider slider = new CustomSlider(1.0, 3.0, 1.0);
        slider.setBlockIncrement(0.1);
        slider.getStyleClass().add("rect-slider");

        Region region = new Region();
        region.setStyle("-fx-background-color: -fx-accent;");
        region.setMaxHeight(5);
        region.setMaxWidth(0);

        region.setMouseTransparent(true);

        StackPane containerSlider = new StackPane(slider, region);
        containerSlider.setAlignment(Pos.CENTER_LEFT);

        getChildren().setAll(first, containerSlider, second);
        HBox.setHgrow(containerSlider, Priority.ALWAYS);

        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            cropImageView.setFitWidth(500 * newValue.doubleValue());
        });

        Platform.runLater(() -> {

//            StackPane d = (StackPane) slider.lookup(".thumb");
//
//            region.maxWidthProperty().bind(d.layoutXProperty());
//            region.prefWidthProperty().bind(d.layoutXProperty());
//            region.minWidthProperty().bind(d.layoutXProperty());


        });
    }
}
