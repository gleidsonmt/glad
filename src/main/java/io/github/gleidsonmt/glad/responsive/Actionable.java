package io.github.gleidsonmt.glad.responsive;

import io.github.gleidsonmt.glad.responsive.sizer.Size;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a single actionable class.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
@FunctionalInterface
public interface Actionable<T extends Size> {


    void doAction(T size);


}
