package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import io.github.gleidsonmt.glad.base.internal.animations.Anchor;
import io.github.gleidsonmt.glad.base.internal.animations.Animations;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class FlowImpl implements Flow {

    private Pos pos = Pos.CENTER;
    private Insets insets = Insets.EMPTY;
    private Anchor anchor = null;
    private Node content;
    private final Root root;

    public FlowImpl(Root root) {
        this.root = root;
    }

    @Override
    public void openAbsolute(Node container) {
        openAbsolute(container, Pos.CENTER, Insets.EMPTY);
    }


    @Override
    public void openAbsolute(Pos pos, Node container, Animations animation) {
        openAbsolute(container, pos, Insets.EMPTY);
    }

    @Override
    public void openAbsolute(Node container, Pos pos) {
        openAbsolute(container, pos, Insets.EMPTY);
    }

    @Override
    public void openAbsolute(Pos pos, Node container, Insets insets, Animations animation) {

    }

    @Override
    public void openAbsolute(Node container, Pos position, Insets insets) {

        StackPane.clearConstraints(container);

        if (container instanceof Region region) {
            if (anchor != null) {
                switch (anchor) {
                    case TOP, BOTTOM -> region.setMaxHeight(Region.USE_PREF_SIZE);
                    case LEFT, RIGHT -> region.setMaxWidth(Region.USE_PREF_SIZE);
                }
            } else {
                region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            }

            StackPane.setAlignment(container, position);
            StackPane.setMargin(container, insets);

            if (!root.getChildren().contains(container)) {
                root.getChildren().add(container);
            }
        }
    }

    @Override
    public void openLeft(Node container) {
        openLeft(container, Insets.EMPTY);
    }

    @Override
    public void openLeft(Node container, Insets insets) {
        openAbsolute(container, Pos.CENTER_LEFT, insets);
        if (container instanceof Region region)
            region.setMaxHeight(Region.USE_COMPUTED_SIZE);
    }

    @Override
    public void openRight(Node container) {
        openRight(container, Insets.EMPTY);
    }

    @Override
    public void openRight(Node container, Insets insets) {
        openAbsolute(container, Pos.CENTER_RIGHT, insets);
        if (container instanceof Region region)
            region.setMaxHeight(Region.USE_COMPUTED_SIZE);
    }

    @Override
    public void openByCursor(Region container, MouseEvent e) {
        openByCursor(container, e, 0,0);
    }

    @Override
    public void openByCursor(Region container, MouseEvent e, double x, double y) {
        if (root.getChildren().contains(container)) return;

        container.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        StackPane.clearConstraints(container);
        StackPane.setAlignment(container, Pos.TOP_LEFT);
        StackPane.setMargin(container, insets);

        container.getStyleClass().add("popup");

        container.layoutBoundsProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                var scene = root;

                double width = container.getWidth();
                double height = container.getHeight();


                double widthNeeded = e.getSceneX() + width;
                double heightNeeded = e.getSceneY() + height;

                double widthAvailable = scene.getWidth();
                double heightAvailable = scene.getHeight();

                container.setTranslateX(widthNeeded > widthAvailable ? (widthAvailable - width) - x : e.getSceneX() - x);
                container.setTranslateY(heightNeeded > heightAvailable ?(heightAvailable - height) + y : e.getSceneY() + y);
            }
        });

        root.getChildren().add(container);

    }

    @Override
    public Flow with(WrapperEffect effect) {
        root.wrapper().show(effect);
        return this;
    }

    @Override
    public void remove(Node container) {
        root.getChildren().remove(container);
    }

    @Override
    public void clear() {
        root.getChildren().removeIf(e ->
                !(e instanceof Layout) && (e.getStyleClass().contains("popup"))
        );
    }

    @Override
    public Flow anchor(Anchor anchor) {
        this.anchor = anchor;
        return this;
    }

    @Override
    public Flow insets(Insets insets) {
        this.insets = insets;
        return this;
    }

    @Override
    public Flow pos(Pos pos) {
        this.pos = pos;
        return this;
    }

    public Flow content(Node content) {
        this.content = content;
        return this;
    }

    @Override
    public void show() {
        if (content == null) {
            throw new RuntimeException("Error flow can invoke a null node.");
        }
        openAbsolute(content, pos, insets);
    }
}
