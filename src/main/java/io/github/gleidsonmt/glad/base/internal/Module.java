package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public interface Module {

    ObservableList<Module> getModules();

    String getName();

    Node getGraphic();

    boolean isAnimated();

    default void onEnter(Layout layout){};

    default void onExit(Layout layout){}
    // Testing
    void setParent(Module parent);

    Module getParent();

    void setContainer(Pane container) ;

    Pane getContainer();
}
