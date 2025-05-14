package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.responsive.Actionable;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.base.responsive.Breaker;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/03/2025
 */
public class Container extends StackPane implements Actionable<Break>, Breaker {

    public Container() {
        new Sizer<>(this, Break.values()) {
            @Override
            public void change(Break aBreak) {
                doAction(aBreak);
            }
        };
    }
}
