package io.github.gleidsonmt.glad.base.drawer;

import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/07/2024
 */
public class Module  {

    private final ObservableList<Module> modules;
    private final String name;
    private Module parent;
    private Pane container;
    private Node graphic;

    private boolean animated;

    public Module(String name,  Module... _modules) {
        this.modules = FXCollections.observableArrayList(_modules);
        this.name = name;

        Arrays.stream(_modules).forEach(el -> el.setParent(this));

        modules.addListener((ListChangeListener<Module>) change -> {
            if(change.next()) {
                modules.forEach(e -> e.setParent(Module.this));
            }
        });

    }

    @ApiStatus.Experimental
    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public ObservableList<Module> getModules() {
        return modules;
    }

    public String getName() {
        return name;
    }

    void setParent(Module parent) {
        this.parent = parent;
    }

    public Module getParent() {
        return parent;
    }

    public Pane getContainer() {
        return container;
    }

    public void setContainer(Pane container) {
        this.container = container;
    }

    @ApiStatus.Experimental
    public boolean isAnimated() {
        return animated;
    }

    public Node getGraphic() {
        return graphic;
    }

//    public void setGraphic(Node graphic) {
//        setGraphic(graphic, false);
//    }



    @Override
    public String toString() {
        return getName();
    }
}
