package io.github.gleidsonmt.glad.base.responsive;


import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/03/2025
 */
public class Container<T extends Size> extends StackPane {

    protected Breaker<T> breaker;
    protected Sizer<T> sizer;

    @SuppressWarnings("unchecked")
    public Container() {
        this((T[]) Break.values());
    }

    public Container(T[] values) {
        this.breaker = new Breaker<>();
        this.sizer = new Sizer<T>(this, values) {
            @Override
            public void change(T size) {
                breaker.doAction(size);
            }
        };
    }

    public void setSizer(Sizer<T> sizer) {
        this.sizer.clear(this);
        this.sizer = sizer;
    }

    /**
     * See the values and the width relation.
     * [ < number] - Size
     */
    public void log() {
        this.sizer.setLog(true);
    }

}
