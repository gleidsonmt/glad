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

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.DefaultProperty;
import javafx.css.*;
import javafx.scene.control.ContentDisplay;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/09/2022
 */
@DefaultProperty("control")
public class FabButton extends Button {

    private static final StyleablePropertyFactory<FabButton> FACTORY =
            new StyleablePropertyFactory<>(Button.getClassCssMetaData());

    private final StyleableObjectProperty<Icon> icon =
            new SimpleStyleableObjectProperty<>(ICON, this, "icon", Icon.NONE);

    private static final CssMetaData<FabButton, Icon> ICON =
            FACTORY.createEnumCssMetaData(Icon.class, "-fx-icon",
                    g -> g.icon, Icon.NONE, true);

    public FabButton() {
        this(null);
    }

    public FabButton(Icon icon) {
        this(icon, false);
    }
    public FabButton(Icon icon, boolean inside) {
        setIcon(icon);
        getStyleClass().addAll("round", inside ? "inside-button" : "icon-button", "padding-0" );
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setGraphic(new SVGIcon());

        if (getIcon() == null || getIcon() == Icon.NONE) {
            ((SVGIcon) getGraphic()).setContent(Icon.FAVORITE);
        } else
            ((SVGIcon) getGraphic()).setContent(getIcon());

        iconProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue != Icon.NONE) {
                ((SVGIcon) getGraphic()).setContent(newValue);
            }
        });
    }

//    @Override
//    protected Skin<?> createDefaultSkin() {
//        return new IconButtonSkin(this);
//    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(GladResources.class.getResource("agents/icon-button.css")).toExternalForm();
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
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

//    @Override
//    protected List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
//        return FACTORY.getCssMetaData();
//    }
}
