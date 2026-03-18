package io.github.gleidsonmt.glad.controls;

import javafx.scene.control.Control;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/03/2024
 */
public interface ComponentSkin<T extends Control> {

    // Bind controls properties
    void bind(T _control);

    // Convenient method to set the initial state when is united with a skin.
    void setInitialState(T _control);

}