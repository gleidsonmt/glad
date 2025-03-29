package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.dialog.Dialog;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/02/2025
 */
public interface Behavior {


    void openDrawer();

    void closeDrawer();

    Alert alert();

    Dialog dialog();

    @ApiStatus.Experimental
    void closeAside();

    @ApiStatus.Experimental
    boolean isDrawerOpen();

    @ApiStatus.Experimental
    void openAside();

    @ApiStatus.Experimental
    boolean isDrawerAbsolute();


}
