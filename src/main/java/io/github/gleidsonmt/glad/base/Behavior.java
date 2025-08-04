package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.dialog.Dialog;
import javafx.scene.Node;
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

    Alert alert();

    Dialog dialog();

    @Deprecated(forRemoval = true)
    void setDrawer(Node drawer);

    void openDrawer();

    void closeDrawer();

}
