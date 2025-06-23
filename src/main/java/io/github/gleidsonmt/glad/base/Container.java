package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.responsive.Actionable;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.base.responsive.BreakPoint;
import io.github.gleidsonmt.glad.base.responsive.Breaker;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
