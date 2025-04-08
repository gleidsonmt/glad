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

package io.github.gleidsonmt.glad.controls;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.skin.GNIconButtonSkin;
import javafx.beans.DefaultProperty;
import javafx.css.*;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/09/2022
 */
@DefaultProperty("control")
public class IconButton extends Control {

    private static final StyleablePropertyFactory<IconButton> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableObjectProperty<Icon> icon =
            new SimpleStyleableObjectProperty<>(ICON, this, "icon", Icon.NONE);

    private static final CssMetaData<IconButton, Icon> ICON =
            FACTORY.createEnumCssMetaData(Icon.class, "-gn-icon",
                    g -> g.icon, Icon.NONE, true);

    public IconButton() {
        this(null);
    }

    public IconButton(Icon icon) {
        setMaxSize(40, 40);
        setMinSize(40, 40);
        setIcon(icon);
        getStyleClass().add("gn-icon-button");
    }



    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNIconButtonSkin(this);
    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(GNIconButton.class.getResource("/io.github.gleidsonmt.gncontrols/agents/button.css")).toExternalForm();
//    }

    public Icon getIcon() {
        return icon.get();
    }

    public StyleableObjectProperty<Icon> iconProperty() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon.set(icon);
    }

    @Override
    protected List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }
}
