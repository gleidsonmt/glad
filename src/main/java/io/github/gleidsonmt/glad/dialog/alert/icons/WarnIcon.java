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
public class WarnIcon extends StackPane {

    public WarnIcon() {
        StackPane.setAlignment(this, Pos.TOP_CENTER);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.getStyleClass().addAll("icon","warn-icon");
        this.getChildren().add(createSVG());
        this.setPrefSize(70,70);
        this.setTranslateY(-40);
        SVGPath svg = new SVGPath();
        svg.setContent("M 200 350 L 350 100 L 500 350 L 200 350");
        this.setShape(svg);
    }

    private Group createSVG() {
        SVGPath pth = new SVGPath();
        pth.setContent("m40-120 440-760 440 760H40Zm138-80h604L480-720 178-200Zm302-40q17 0 28.5-11.5T520-280q0-17-11.5-28.5T480-320q-17 0-28.5 11.5T440-280q0 17 11.5 28.5T480-240Zm-40-120h80v-200h-80v200Zm40-100Z");
        pth.setScaleX(0.10);
        pth.setScaleY(0.10);

        Group group = new Group(pth);
        group.setAutoSizeChildren(true);
        return group;
    }
}
