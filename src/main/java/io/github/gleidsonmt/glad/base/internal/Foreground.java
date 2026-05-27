

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.geometry.Insets;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  13/08/2025
 */
public class Foreground extends StackPane {


    public Foreground restyle(WrapperEffect effect, Root root) {
        if (effect == null || effect == WrapperEffect.NONE) {
            root.getChildren().getFirst().setEffect(null);
            this.setBackground(null);
            return this;
        }

        if (effect.equals(WrapperEffect.BLUR)) {
            root.getChildren().getFirst().setEffect(new BoxBlur(2, 2, 1));
//            getScene().getRoot().setEffect(new BoxBlur(2, 2, 1));
            this.setBackground(new Background(
                    new BackgroundFill(Color.rgb(255, 255, 255, 0.1), CornerRadii.EMPTY, Insets.EMPTY)
            ));
        } else {
            root.getChildren().getFirst().setEffect(null);
//            getScene().getRoot().setEffect(null);
            this.setBackground(
                    new Background(
                            new BackgroundFill(
//                                Color.gray(0.5, 0.3),
                                    Color.gray(0.5, 0.3),
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY)
                    )
            );
        }
        return this;
    }
}
