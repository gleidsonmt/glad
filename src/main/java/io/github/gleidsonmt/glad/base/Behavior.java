package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.dialog.Alert;
import io.github.gleidsonmt.glad.base.dialog.Dialog;
import io.github.gleidsonmt.glad.base.dialog.snack.Snack;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/02/2025
 */
public interface Behavior {

    @ApiStatus.Experimental
    void closeAside();

    @ApiStatus.Experimental
    boolean isDrawerOpen();

    @ApiStatus.Experimental
    void openAside();

    @ApiStatus.Experimental
    boolean isDrawerAbsolute();

    /******************************************************************
     *
     *                          Fixed
     *
     *****************************************************************/

    Dialog dialog();

    Alert alert();

    Snack snack();

    void openDrawer();

    void closeDrawer();

}
