package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.WrapperEffect;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/09/2025
 */

@SuppressWarnings("unchecked")
public class DialogAbstract<T> extends FlowItemAbstract<T> implements DialogBase<T> {

    protected String title;
    protected Region content;
    protected WrapperEffect effect = WrapperEffect.GRAY;
    protected double width = -1;
    protected double height = -1;
    protected boolean full = false;

    @Override
    public T width(double width) {
        this.width = width;
        return (T) this;
    }

    @Override
    public T height(double height) {
        this.height = height;
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
        this.effect = effect;
        return (T) this;
    }

    @Override
    public T full() {
        this.full = true;
        return (T) this;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }
}
