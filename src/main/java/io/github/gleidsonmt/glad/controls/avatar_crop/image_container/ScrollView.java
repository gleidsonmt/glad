package io.github.gleidsonmt.glad.controls.avatar_crop.image_container;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/11/2024
 */
public class ScrollView extends ScrollPane {

    private AvatarCropImageView scrollViewContent;
    private StackPane wrapper = new StackPane();

    public ScrollView(Image image) {

        wrapper.setMinWidth(500);
        wrapper.setMinHeight(200);

        scrollViewContent = new AvatarCropImageView(image);
        this.setContent(wrapper);

        wrapper.setAlignment(Pos.CENTER);
        StackPane.setAlignment(wrapper, Pos.CENTER);

        wrapper.getChildren().add(scrollViewContent);
//        StackPane.setAlignment(scrollViewContent, Pos.CENTER);

//        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//        this.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        double max = 500;
        this.setMaxWidth(max);
        this.setMinWidth(max);
//            this.setMaxHeight(max);
//        this.setMinHeight(max);


//        setFitToWidth(true);
//        this.setFitToHeight(true);
//
        this.setPannable(true);
//        this.setHbarPolicy(ScrollBarPolicy.NEVER);
//        this.setVbarPolicy(ScrollBarPolicy.NEVER);
//
        this.getStyleClass().add("picture-select-scroll");
//
//        VBox.setVgrow(this, Priority.ALWAYS);
    }

    public AvatarCropImageView getScrollViewContent() {
        return scrollViewContent;
    }
}
