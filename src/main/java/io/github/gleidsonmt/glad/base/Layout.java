package io.github.gleidsonmt.glad.base;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public interface Layout  {

    default Region getDrawer() { return null; }

    default Region getAside() { return null; }

    default Node getBar() { return null; }

    default Node getFooter() { return null; }

    default Node setDrawer() {
        return null;
    }

    @ApiStatus.Experimental
    default void updateView(Module oldVal, Module newVal) {}

    @ApiStatus.Experimental
    ObjectProperty<Module> currentModuleProperty();

    @ApiStatus.Experimental
    default Module getCurrentModule() { return currentModuleProperty().get(); }

    @ApiStatus.Experimental
    default void setCurrentModule(Module module) { currentModuleProperty().set(module); }

}
