

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.base.dialog.Wrapper;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  26/01/2025
 */
@Deprecated
public class WrapperImpl extends FlowItemAbstract<Wrapper> implements Wrapper {

    private final Root root;
    private final Foreground foreground;

    private Flow flow;

    private Region with;

    private EventHandler<MouseEvent> onClick;

    public WrapperImpl(Root root) {
        this.root = root;
        this.foreground = new Foreground();

        this.foreground.addEventFilter(MouseEvent.MOUSE_RELEASED, _ -> {
        });
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
                .content(foreground.restyle(effect, root))
                .show();

        if (with != null) {
            root.flow()
                    .anchor(anchor)
                    .pos(pos)
                    .content(with)
                    .insets(insets)
                    .show();
        }

        foreground.setOnMousePressed(onClick);


//        if (!root.getChildren().contains(foreground)) {
//            root.getChildren().add(foreground);
//        }

//        if (effect.equals(WrapperEffect.BLUR)) {
//            root.getChildren().getFirst().setEffect(new BoxBlur(2, 2, 1));
//            foreground.setBackground(new Background(
//                    new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)
//            ));
//        } else {
//            root.getChildren().getFirst().setEffect(null);
//            foreground.setBackground(
//                    new Background(
//                            new BackgroundFill(
////                                Color.gray(0.5, 0.3),
//                                    Color.gray(0.5, 0.3),
//                                    CornerRadii.EMPTY,
//                                    Insets.EMPTY)
//                    )
//            );
//        }


    }

    @Override
    public Wrapper onClick(EventHandler<MouseEvent> eventHandler) {
        this.onClick = eventHandler;
        return this;
    }

    @Override
    public Wrapper with(Region node) {
        this.with = node;
        return this;
    }

    @Override
    public Wrapper with(Flow node) {
        this.flow = node;
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
