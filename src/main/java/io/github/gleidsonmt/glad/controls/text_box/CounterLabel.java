

package io.github.gleidsonmt.glad.controls.text_box;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  22/09/2022
 */
public class CounterLabel extends Label {

    private final IntegerProperty act = new SimpleIntegerProperty(0);
    private final IntegerProperty max = new SimpleIntegerProperty();

    public CounterLabel() {
        act.addListener((observable, oldValue, newValue) -> update());
        max.addListener((observable, oldValue, newValue) -> update());
    }

    private void update() {
        if (max.intValue() == -1) {
            setText(act.get() + "/..");
        } else setText(act.get() + "/" + max.get());
    }

    public int getCount() {
        return act.get();
    }

    public IntegerProperty actProperty() {
        return act;
    }

    public void setCount(int act) {
        this.act.set(act);
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }
}
