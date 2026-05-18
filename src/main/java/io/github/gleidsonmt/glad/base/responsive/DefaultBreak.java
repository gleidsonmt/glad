

package io.github.gleidsonmt.glad.base.responsive;

import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import org.jetbrains.annotations.Contract;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  26/09/2024
 */
public enum DefaultBreak implements Size {

    SM(640),
    MD(768),
    LG(1024),
    XL(1280),
    XXL(1536),
    WIDE(2560),
    ;

    private final double size;

    @Contract(pure = true)
    DefaultBreak(double size) {
        this.size = size;
    }

    @Contract(pure = true)
    public double getMax() {
        return size;
    }

    public Size convert(String string) {
        return DefaultBreak.valueOf(string);
    }
}
