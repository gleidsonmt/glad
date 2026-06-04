

/*
 * *
 *  * Description:
 *  *
 *  * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 *  * Create on ${DATE}
 *
 */

package io.github.gleidsonmt.glad.base.dialog.alert.layout;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  07/11/2024
 */
public class BarAction extends ButtonBar {

    public BarAction() {
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setPadding(new Insets(0, 10, 0, 0));
    }
}
