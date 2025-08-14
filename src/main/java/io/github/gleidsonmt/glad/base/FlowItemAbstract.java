package io.github.gleidsonmt.glad.base;

import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/08/2025
 */
@SuppressWarnings("unchecked")
public abstract class FlowItemAbstract<T> implements FlowItem<T> {

    protected Node content;
    protected Pos pos;
    protected WrapperEffect effect = WrapperEffect.GRAY;

    @Override
    public abstract void show();

    @Override
    public abstract void hide();

    @Override
    public T content(Node content) {
        this.content = content;
        return (T) this;
    }

    @Override
    public T pos(Pos pos) {
        this.pos = pos;
        return (T) this;
    }
}
