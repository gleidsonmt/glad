

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;


/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  04/09/2025
 */

@SuppressWarnings("unchecked")
public class DialogAbstract<T> extends FlowItemAbstract<T> implements DialogBase<T> {

    protected String title;
    protected Region content;
    protected DoubleProperty width = new SimpleDoubleProperty(-1);
    protected DoubleProperty height = new SimpleDoubleProperty(-1);
    protected boolean full = false;


    public DialogAbstract(Root root) {
        super(root);
//        this.foreground.addEventFilter(MouseEvent.MOUSE_PRESSED, _ -> {
////            root.behavior().closeDrawer();
////            root.behavior().closeAside();
//        });

//        this.foreground.addEventFilter(MouseEvent.MOUSE_CLICKED, hideEvent);
    }

    private final EventHandler<MouseEvent> hideEvent = _ -> {
//        root.getChildren().remove(foreground);
        hide();
    };

    protected void blockForeground() {
//        this.foreground.removeEventFilter(MouseEvent.MOUSE_CLICKED, hideEvent);
    }

    @Override
    public T width(double width) {
        this.width.set(width);
        return (T) this;
    }

    @Override
    public T height(double height) {
        this.height.set(height);
        return (T) this;
    }

    @Override
    public T title(String title) {
        this.title = title;
        return (T) this;
    }

    @Override
    public T content(Region node) {
        this.content = node;
        return (T) this;
    }

    @Override
    public T full() {
        this.anchor = Anchor.FULL;
        return (T) this;
    }

    @Override
    public void reset() {
        super.reset();
        this.width.set(-1);
        this.height.set(-1);
    }
}
