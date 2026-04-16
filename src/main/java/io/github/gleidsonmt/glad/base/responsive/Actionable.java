package io.github.gleidsonmt.glad.base.responsive;

import io.github.gleidsonmt.glad.base.responsive.sizer.Size;

/**
 * A single triggered action.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
@FunctionalInterface
public interface Actionable<T extends Size> {
    void doAction(T size);
}
