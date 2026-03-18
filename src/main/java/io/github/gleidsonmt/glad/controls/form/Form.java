package io.github.gleidsonmt.glad.controls.form;

import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/04/2024
 */
public interface Form<T> {

    boolean validate();

    boolean persist();

    T get();
}
