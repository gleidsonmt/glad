package io.github.gleidsonmt.glad.responsive_grid;

import io.github.gleidsonmt.glad.responsive_grid.sizer.Size;

/**
 * Represents a single actionable class.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
@FunctionalInterface
public interface Actionable<T extends Size> {

    void doAction(T size);

}
