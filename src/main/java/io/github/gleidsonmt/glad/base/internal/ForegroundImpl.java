package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
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
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  13/08/2025
 */
public class ForegroundImpl extends StackPane implements Foreground {

    private final Root root;
    private EventHandler<MouseEvent> eventHandler;

    public ForegroundImpl(Root root) {
        setId("foreground");
        this.root = root;
    }

    @Override
    public void restyle(WrapperEffect effect) {
        if (effect == null) effect = WrapperEffect.NONE;
        switch (effect) {
            case WrapperEffect.BLUR -> {
                root.getContent().setEffect(new BoxBlur(2, 2, 1));
                this.setBackground(new Background(new BackgroundFill(
                        Color.rgb(255, 255, 255, 0.1), CornerRadii.EMPTY, Insets.EMPTY)
                ));
            }
            case WrapperEffect.GRAY -> {
                root.getContent().setEffect(null);
                this.setBackground(new Background(new BackgroundFill(
                        Color.gray(0.5, 0.3), CornerRadii.EMPTY, Insets.EMPTY)
                ));
            }
            case WrapperEffect.NONE -> {
                root.getContent().setEffect(null);
                this.setBackground(new Background(new BackgroundFill(
                        Color.TRANSPARENT,
                        CornerRadii.EMPTY,
                        Insets.EMPTY)));
            }
            default -> {
                this.setEffect(null);
                this.setBackground(new Background(new BackgroundFill(
                        Color.WHITE,
                        CornerRadii.EMPTY,
                        Insets.EMPTY)));
            }
        }
    }

    @Override
    public void show() {
        this.toFront();
    }

    @Override
    public void hide() {
        this.toBack();
    }

    @Override
    public void addAction(EventHandler<MouseEvent> eventHandler) {
        this.eventHandler = eventHandler;
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    @Override
    public void removeAction() {
        this.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

}
