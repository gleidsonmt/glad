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
public class InfoIcon extends StackPane {

    public InfoIcon() {
        StackPane.setAlignment(this, Pos.TOP_CENTER);
//        this.setPrefSize(70, 70);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.getStyleClass().addAll("icon", "info-icon");
        this.getChildren().addAll(createBlankRegion(), createSVG());
        this.setTranslateY(-40);
    }

    private Region createBlankRegion() {
        Region region = new Region();
        region.setRotate(-45);
        region.setPrefSize(50, 50);
        region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return region;
    }

    private Group createSVG() {
        SVGPath pth = new SVGPath();
        pth.setContent("M480-79q-16 0-30.5-6T423-102L102-423q-11-12-17-26.5T79-480q0-16 6-31t17-26l321-321q12-12 26.5-17.5T480-881q16 0 31 5.5t26 17.5l321 321q12 11 17.5 26t5.5 31q0 16-5.5 30.5T858-423L537-102q-11 11-26 17t-31 6Zm0-80 321-321-321-321-321 321 321 321Zm-40-281h80v-240h-80v240Zm40 120q17 0 28.5-11.5T520-360q0-17-11.5-28.5T480-400q-17 0-28.5 11.5T440-360q0 17 11.5 28.5T480-320Zm0-160Z");
        pth.setScaleX(0.1);
        pth.setScaleY(0.1);
        pth.setRotate(-180);

        Group group = new Group(pth);
        group.setAutoSizeChildren(true);
        return group;
    }
}
