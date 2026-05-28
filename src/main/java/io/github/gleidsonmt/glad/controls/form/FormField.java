

package io.github.gleidsonmt.glad.controls.form;

import javafx.beans.property.BooleanProperty;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  17/02/2026
 */
@ApiStatus.Experimental
public interface FormField {

    void validate();

    boolean isValid();

    BooleanProperty validProperty();

}
