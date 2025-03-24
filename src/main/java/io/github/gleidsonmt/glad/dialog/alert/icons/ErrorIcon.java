package io.github.gleidsonmt.glad.dialog.alert.icons;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/11/2024
 */
public class ErrorIcon extends StackPane {

    public ErrorIcon() {
        StackPane.setAlignment(this, Pos.TOP_CENTER);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.getStyleClass().addAll("icon", "error-icon");
        this.getChildren().add(createSVG());
        this.setPrefSize(70, 70);
        this.setMaxSize(70, 70);
    }

    private Group createSVG() {
        SVGPath pth = new SVGPath();
        pth.setContent("m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z");
        pth.setScaleX(0.06);
        pth.setScaleY(0.06);

        Group group = new Group(pth);
        group.setAutoSizeChildren(true);
        return group;
    }
}
