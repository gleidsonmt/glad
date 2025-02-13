package io.github.gleidsonmt.glad.controls.icon;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
//import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/12/2024
 */
public class SVGPathIcon extends Group {

    protected final SVGPath path = new SVGPath();

    public SVGPathIcon(SVGPath icon, Color color, double size) {
        setContentAndResize(icon, size);
        setMouseTransparent(true);
        this.getStyleClass().add("icon-container");
        path.getStyleClass().add("icon");

        path.setFill(color == null ? Color.web("#656D78") : color);
//        setFill(color);
//        name = icon.name();


        setAutoSizeChildren(true);
    }

    private void setContentAndResize( SVGPath icon, double size) {
        path.setContent(icon.getContent());
        this.getChildren().add(path);
        setScale(size);
//        name = icon.name();

//        this.autosize();
//        this.applyCss();
//        this.layoutChildren();
    }

    public void setScale(double size){
        path.setScaleX(0.023 * size);
        path.setScaleY(0.023 * size);
    }
}
