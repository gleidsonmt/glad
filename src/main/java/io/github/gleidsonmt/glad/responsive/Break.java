package io.github.gleidsonmt.glad.responsive;

import io.github.gleidsonmt.glad.responsive.sizer.Size;
import org.jetbrains.annotations.Contract;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/09/2024
 */
public enum Break implements Size {

    SM(640),
    MD(768),
    LG(1024),
    XL(1280),
    XXL(1536),
    WIDE(2560),
//    ALL(5000)
    ;

    private final double size;

    Break(double size) {
        this.size = size;
    }

    @Contract(pure = true)
    public double getMax() {
        return size;
    }
}
