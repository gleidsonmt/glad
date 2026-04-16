package io.github.gleidsonmt.glad.base;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public interface Module {

    String getName();

    Node getGraphic();

    default Node getNode() {
        return null;
    }

    default void setNode(Node node) {
    }

    ;

    @Deprecated(forRemoval = true)
    boolean isAnimated();

    @ApiStatus.Experimental
    default void onExit(Layout layout) {
    }

    @ApiStatus.Experimental
    default void onEnter(Layout layout) {
    }

    ;

    // Testing
    default ObservableList<Module> getModules() {
        return null;
    }

    default void setParent(Module parent) {
    }

    default Module getParent() {
        return null;
    }

    default void setContainer(Pane container) {
    }

    ;

    default Pane getContainer() {
        return null;
    }
}
