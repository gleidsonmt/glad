package io.github.gleidsonmt.glad.controls.form;

import javafx.beans.property.BooleanProperty;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/02/2026
 */
public interface FormField {

    void validate();

    boolean isValid();

    BooleanProperty validProperty();

}
