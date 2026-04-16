package io.github.gleidsonmt.glad.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
@SuppressWarnings("unused")
public interface Layout {

    @ApiStatus.Experimental
    ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

    default void setLeft(Node node) {
    }

    default Node getLeft() {
        return null;
    }

    default void setRight(Node node) {
    }

    default Node getRight() {
        return null;
    }

    default void setTop(Node node) {
    }

    default Node getTop() {
        return null;
    }

    default void setBottom(Node node) {
    }

    default Node getBottom() {
        return null;
    }

    default void setCenter(Node node) {
    }

    default Node getCenter() {
        return null;
    }

    @ApiStatus.Experimental
    default Node getBar() {
        return null;
    }

    @ApiStatus.Experimental
    default Node getFooter() {
        return null;
    }

    @ApiStatus.Experimental
    default void updateView(Module oldVal, Module newVal) {
    }

    @ApiStatus.Experimental
    default ObjectProperty<Module> currentModuleProperty() {
        return currentModule;
    }

    @ApiStatus.Experimental
    default Module getModule() {
        return currentModule.get();
    }

    @ApiStatus.Experimental
    default void setModule(Module module) {
        currentModule.set(module);
    }
}
