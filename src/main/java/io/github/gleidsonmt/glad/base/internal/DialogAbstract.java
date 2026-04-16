package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/09/2025
 */

@SuppressWarnings("unchecked")
public class DialogAbstract<T> extends FlowItemAbstract<T> implements DialogBase<T> {

    protected String title;
    protected Region content;
    protected WrapperEffect wrapperEffect = null;
    protected DoubleProperty width = new SimpleDoubleProperty(-1);
    protected DoubleProperty height = new SimpleDoubleProperty(-1);
    protected boolean full = false;
    protected Foreground foreground;

    protected final Root root;

    public DialogAbstract(Root root) {
        this.foreground = new Foreground();
        this.root = root;

//        this.foreground.addEventFilter(MouseEvent.MOUSE_RELEASED, _ -> {
////            root.behavior().closeDrawer();
////            root.behavior().closeAside();
//        });

        this.foreground.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> {
            this.hide();
            foreground.restyle(null, root);
        });
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
    public T effect(WrapperEffect effect) {
        this.wrapperEffect = effect;
        return (T) this;
    }

    @Override
    public T full() {
        this.anchor = Anchor.FULL;
        return (T) this;
    }

    @Override
    public void show() {
        showing = true;
    }

    @Override
    public void hide() {
        showing = false;
    }


}
