/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.glad.controls.button;

import javafx.beans.DefaultProperty;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/09/2022
 */
@DefaultProperty("control")
public class IconButton extends Button {

    public IconButton() {
        this(null);
    }

    public IconButton(Node icon) {
        this(icon, false);
    }

    public IconButton(Node icon, boolean inside) {
        setGraphic(icon);
        getStyleClass().addAll(inside ? "inside-button" : "icon-button", "w-40", "h-40");
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    public void setIcon(Node icon) {
        setGraphic(icon);
    }

}