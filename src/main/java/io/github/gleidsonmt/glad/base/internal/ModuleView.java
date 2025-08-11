package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Layout;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class ModuleView implements Module {

    private final ObservableList<Module> modules;
    private final String name;
    private Module parent;
    private Pane container;
    private Node graphic;

    private boolean animated;

    public ModuleView(String name, Module... _modules) {
        this.modules = FXCollections.observableArrayList(_modules);
        this.name = name;

//        if (_modules != null) {
//        Arrays.stream(_modules).forEach(el -> el.setParent(this));
        Arrays.stream(_modules).forEach(el -> {
             el.setParent(this);
        });


        modules.addListener((ListChangeListener<Module>)change ->

    {
        if (change.next()) {
            modules.forEach(el -> el.setParent(ModuleView.this));
        }
    });

}

@ApiStatus.Experimental
public void setAnimated(boolean animated) {
    this.animated = animated;
}

@Override
public ObservableList<Module> getModules() {
    return modules;
}

@Override
public String getName() {
    return name;
}

@Override
public Module getParent() {
    return parent;
}

@Override
public Pane getContainer() {
    return container;
}

@Override
public void setContainer(Pane container) {
    this.container = container;
}

@ApiStatus.Experimental
@Override
public boolean isAnimated() {
    return animated;
}

@Override
public void onEnter(Layout layout) {

}

@Override
public void onExit(Layout layout) {

}

@Override
public Node getGraphic() {
    return graphic;
}

@Override
public void setParent(Module parent) {
    this.parent = parent;
}

//    public void setGraphic(Node graphic) {
//        setGraphic(graphic, false);
//    }


@Override
public String toString() {
    return getName();
}
}
