package io.github.gleidsonmt.glad.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public interface Layout  {

    void setLeft(Node node);

    Node getLeft();

    void setRight(Node node);

    void setTop(Node node);

    void setBottom(Node node);

    void setCenter(Node node);

    @ApiStatus.Experimental
    ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

    @ApiStatus.Experimental
    default Node getBar() { return null; }

    @ApiStatus.Experimental
    default Node getFooter() { return null; }

    @ApiStatus.Experimental
    default void updateView(Module oldVal, Module newVal) {}

    @ApiStatus.Experimental
    default ObjectProperty<Module> currentModuleProperty() { return currentModule; }

    @ApiStatus.Experimental
    default Module getCurrentModule() { return currentModule.get(); }

    @ApiStatus.Experimental
    default void setCurrentModule(Module module) { currentModule.set(module); }

}
