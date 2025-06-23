package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.RootImpl;
import io.github.gleidsonmt.glad.base.Wrapper;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class WrapperImpl implements Wrapper {

    private final RootImpl rootImpl;
    private final StackPane foreground;

    private EventHandler<MouseEvent> onClick;

    public WrapperImpl(RootImpl rootImpl) {
        this.rootImpl = rootImpl;
        this.foreground = new StackPane();

        this.foreground.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            rootImpl.behavior().closeDrawer();
            rootImpl.behavior().closeAside();
        });

    }

    @Override
    public void show() {
        show(WrapperEffect.GRAY);
    }

    @Override
   public void show(WrapperEffect effect) {
        if (!rootImpl.getChildren().contains(foreground)) {
            rootImpl.getChildren().add(foreground);
        }

        if (effect.equals(WrapperEffect.BLUR)) {
            rootImpl.getChildren().get(0).setEffect(new BoxBlur(2,2,1));
        }
        foreground.setBackground(
                new Background(
                        new BackgroundFill(
//                                Color.gray(0.5, 0.3),
                                Color.gray(0.5, 0.3),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                )
        );

        foreground.setOnMousePressed(onClick);

    }

    @Override
    public void setOnClick(EventHandler<MouseEvent> eventHandler) {
        this.onClick = eventHandler;
    }

    @Override
    public void hide() {
        rootImpl.getChildren().remove(foreground);
        rootImpl.getChildren().getFirst().setEffect(null);
    }

    @Override
    public void back() {
        foreground.toBack();
    }

    @Override
    public boolean isShowing() {
        return rootImpl.getChildren().contains(foreground);
    }
}
