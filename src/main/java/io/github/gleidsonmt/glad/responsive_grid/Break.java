package io.github.gleidsonmt.glad.responsive_grid;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/09/2024
 */
public enum Break {

    SM(640),
    MD(768),
    LG(1024),
    XL(1280),
    XXL(1536),
    WIDE(2560),
    ALL(0)
    ;

    private final double size;

    Break(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

}
