

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.base.dialog.Wrapper;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  26/01/2025
 */
@Deprecated(forRemoval = true)
public class WrapperImpl extends FlowItemAbstract<Wrapper> implements Wrapper {

    private final Root root;
    private final Foreground foreground;

    private EventHandler<MouseEvent> onClick;

    public WrapperImpl(Root root) {
        super(root);
        this.foreground = new ForegroundImpl(root);

        this.root = root;
    }

    @Override
    public void show() {
        show(WrapperEffect.GRAY);
    }

    @Override
    public void show(WrapperEffect effect) {

        root.flow()
                .anchor(Anchor.FULL)
                .pos(Pos.CENTER)
//                .content(foreground.restyle(effect, root))
                .show();

        if (with != null) {
            root.flow()
                    .anchor(anchor)
                    .pos(pos)
                    .insets(insets)
                    .show();
        }

//        foreground.setOnMousePressed(onClick);
    }

    @Override
    public Wrapper onClick(EventHandler<MouseEvent> eventHandler) {
        this.onClick = eventHandler;
        return this;
    }

    @Override
    public Wrapper with(Flow node) {
        return this;
    }


    @Override
    public void hide() {
        root.getChildren().remove(foreground);
        root.getChildren().getFirst().setEffect(null);
    }

    @Override
    public boolean isShowing() {
        return root.getChildren().contains(foreground);
    }
}
