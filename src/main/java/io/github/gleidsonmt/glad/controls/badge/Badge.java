/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.glad.controls.badge;

import io.github.gleidsonmt.glad.controls.Component;
import io.github.gleidsonmt.glad.controls.RegionType;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.toggle_switch.ToggleSwitch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.*;
import javafx.css.converter.ColorConverter;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public final class Badge extends IconButton {

    private final ObjectProperty<Icon> icon = new SimpleObjectProperty<>(this, "icon");
    private final IntegerProperty numberOfNotifications = new SimpleIntegerProperty(this, "numberOfNotification",0);

    private final StyleableProperty<Color> boxColor =
            new SimpleStyleableObjectProperty<>(BOX_COLOR, this, "color", Color.RED);

    private final StyleableObjectProperty<RegionType> type =
            new SimpleStyleableObjectProperty<>(REGION_TYPE, this, "type", RegionType.ROUND);

    // StyleablePropertyFactory
    private static final StyleablePropertyFactory<Badge> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());


    public Badge() {
        this(Icon.NOTIFICATION_IMPORTANT, 0);
    }

    public Badge(Icon _icon) {
        this(_icon, 0);
    }

    public Badge(Icon _icon, int number) {
        super(new SVGIcon(_icon, 1.2), true);
        getStyleClass().addAll(  "size-40", "max-size-40", "min-size-40");
        setCursor(Cursor.HAND);
        numberOfNotifications.set(number);
        setAlignment(Pos.TOP_RIGHT);

    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new BadgeSkin(this);
    }

    // CssMetaData from StyleablePropertyFactory
    private static final CssMetaData<Badge, Color> BOX_COLOR =
            FACTORY.createColorCssMetaData("-fx-box-color", s -> s.boxColor, Color.RED, false);

    private static final CssMetaData<Badge, RegionType> REGION_TYPE =
            FACTORY.createEnumCssMetaData(
                    RegionType.class, "-fx-type",
                    g -> g.type);


    // Return all CssMetadata information from StyleablePropertyFactory
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }

    public Icon getIcon() {
        return icon.get();
    }

    public ObjectProperty<Icon> iconProperty() {
        return icon;
    }

    public RegionType getType() {
        return this.type.getValue();
    }

    public void setType(final RegionType type) {
        this.type.setValue(type);
    }

    public ObjectProperty<RegionType> typeProperty() {
        return this.type;
    }

    public Color getBoxColor() {
        return this.boxColor.getValue();
    }

    public void setBoxColor(final Color color) {
        this.boxColor.setValue(color);
    }

    public ObjectProperty<Color> boxColorProperty() {
        return (ObjectProperty<Color>) this.boxColor;
    }

    public int getNumberOfNotifications() {
        return numberOfNotifications.get();
    }

    public IntegerProperty numberOfNotificationsProperty() {
        return numberOfNotifications;
    }

    public void setNumberOfNotifications(int numberOfNotifications) {
        this.numberOfNotifications.set(numberOfNotifications);
    }

}
