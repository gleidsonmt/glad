

package io.github.gleidsonmt.glad.base.internal;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  14/08/2025
 */
public interface AppearItem {
    /**
     * Method called to put the content on the screen.
     */
    void show();

    /**
     * Method called to remove the content on the screen.
     */
    void hide();

    boolean isShowing();
}