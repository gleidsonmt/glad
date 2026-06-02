

package io.github.gleidsonmt.glad.controls.avatar_crop.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  14/11/2024
 */
public class BorderCircle extends Circle {

    public BorderCircle(double radius) {
        super(radius);

        this.setRadius(radius);
        this.setMouseTransparent(true);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.WHITE);
        this.setStrokeWidth(4);
    }
}
