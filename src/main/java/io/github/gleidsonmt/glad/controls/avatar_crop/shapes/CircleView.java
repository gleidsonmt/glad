

package io.github.gleidsonmt.glad.controls.avatar_crop.shapes;

import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  14/11/2024
 */
public class CircleView extends Circle {

    public CircleView(double radius) {
        super(radius);

        this.setFill(Color.WHITE);
        this.setBlendMode(BlendMode.MULTIPLY);
        this.setStroke(Color.WHITE);
        this.setStrokeWidth(15);
        this.setMouseTransparent(true);
    }
}
