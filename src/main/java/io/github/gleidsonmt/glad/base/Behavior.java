package io.github.gleidsonmt.glad.base;


import io.github.gleidsonmt.glad.base.dialog.Alert;
import io.github.gleidsonmt.glad.base.dialog.Dialog;
import io.github.gleidsonmt.glad.base.dialog.snack.Snack;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  01/02/2025
 */
public interface Behavior {

    Dialog dialog();

    Alert alert();

    Snack snack();

    Drawer drawer();

}
