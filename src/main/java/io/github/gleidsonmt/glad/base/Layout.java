

package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.drawer.Module;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  10/06/2025
 */
@SuppressWarnings("unused")
@ApiStatus.Experimental
@Deprecated(forRemoval = true)
public interface Layout {

    @ApiStatus.Experimental
    ObjectProperty<io.github.gleidsonmt.glad.base.drawer.Module> currentModule = new SimpleObjectProperty<>();

    default void setLeft(Node node) {
    }

    default Node getLeft() {
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
    default void updateView(io.github.gleidsonmt.glad.base.drawer.Module oldVal, Module newVal) {
    }

    @ApiStatus.Experimental
    default ObjectProperty<io.github.gleidsonmt.glad.base.drawer.Module> currentModuleProperty() {
        return currentModule;
    }

    @ApiStatus.Experimental
    default io.github.gleidsonmt.glad.base.drawer.Module getModule() {
        return currentModule.get();
    }

    @ApiStatus.Experimental
    default void setModule(io.github.gleidsonmt.glad.base.drawer.Module module) {
        currentModule.set(module);
    }
}
