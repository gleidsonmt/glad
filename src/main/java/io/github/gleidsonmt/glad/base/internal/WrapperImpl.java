package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.Wrapper;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
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

    private final Root root;
    private final StackPane foreground;

    private EventHandler<MouseEvent> onClick;

    public WrapperImpl(Root root) {
        this.root = root;
        this.foreground = new StackPane();

        this.foreground.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            root.behavior().closeDrawer();
            root.behavior().closeAside();
        });

    }

    @Override
    public void show() {
        show(WrapperEffect.GRAY);
    }

    @Override
   public void show(WrapperEffect effect) {
        if (!root.getChildren().contains(foreground)) {
            root.getChildren().add(foreground);
        }

        if (effect.equals(WrapperEffect.BLUR)) {
            root.getChildren().get(0).setEffect(new BoxBlur(2,2,1));
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
    public void close() {
        root.getChildren().remove(foreground);
        root.getChildren().getFirst().setEffect(null);
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
