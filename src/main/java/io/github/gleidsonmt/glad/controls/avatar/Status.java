package io.github.gleidsonmt.glad.controls.avatar;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/04/2025
 */
public enum Status {
    ONLINE, OFFLINE, BUSY, AWAY;

    @Override
    public String toString() {
        return super.name().charAt(0) + super.name().substring(1).toLowerCase();
    }


}
