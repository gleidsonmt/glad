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
import javafx.css.*;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/09/2022
 */
@DefaultProperty("control")
public class Button extends javafx.scene.control.Button {

    private static final StyleablePropertyFactory<Button> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableProperty<Color> circleFill =
            new SimpleStyleableObjectProperty<>(CIRCLE_FILL, this, "circleFill", Color.WHITE);

    private static final CssMetaData<Button, Color> CIRCLE_FILL =
            FACTORY.createColorCssMetaData("-fx-riple-fill",
                    Button::circleFillProperty, Color.WHITE, true);

    public Button() {
        this(null);
    }

    public Button(String text) {
        setText(text == null ? "Button" : text);
        setAlignment(Pos.CENTER);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ButtonSkin(this);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public StyleableProperty<Color> circleFillProperty() {
        return circleFill;
    }

    public Color getCircleFill() {
        return circleFill.getValue();
    }
}