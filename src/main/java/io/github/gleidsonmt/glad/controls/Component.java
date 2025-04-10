package io.github.gleidsonmt.glad.controls;

import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/04/2025
 */
public interface Component<T extends Node> extends ComponentAction {


    default void addClasses(String classes) {
         retrieveClasses((Node) this, classes);
    }

}
