package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.Root;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/01/2025
 */
public class FlowImpl implements Flow {

    private final Root root;

    public FlowImpl(Root root) {
        this.root = root;
    }

    @Override
    public void openAbsolute(Node container, Pos position, Insets insets) {
        StackPane.clearConstraints(container);
        if (container instanceof Region region) {
            region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        }
        StackPane.setAlignment(container, position);
        StackPane.setMargin(container, insets);

        if (!root.getChildren().contains(container)) {
            root.getChildren().add(container);
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
    public void remove(Node container) {
        root.getChildren().remove(container);
    }
}
