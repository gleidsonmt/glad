

package io.github.gleidsonmt.glad.controls.avatar;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  22/04/2025
 */
public enum Status {
    ONLINE, OFFLINE, BUSY, AWAY;

    @Override
    public String toString() {
        return super.name().charAt(0) + super.name().substring(1).toLowerCase();
    }


}
