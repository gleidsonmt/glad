package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.Anchor;
import javafx.geometry.Insets;
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
    protected Insets insets = Insets.EMPTY;
    protected Anchor anchor = Anchor.CENTER;

    @Override
    public T anchor(Anchor anchor) {
        this.anchor = anchor;
        return (T) this;
    }

    @Override
    public T insets(Insets insets) {
        this.insets = insets;
        return (T) this;
    }

    @Override
    public T pos(Pos pos) {
        this.pos = pos;
        return (T) this;
    }
}
