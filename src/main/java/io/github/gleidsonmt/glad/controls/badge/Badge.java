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

import io.github.gleidsonmt.glad.controls.badge.BadgeType;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.*;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Contract;

import java.util.List;

@SuppressWarnings("unused")
public final class Badge extends IconButton {

    private final ObjectProperty<Icon> icon = new SimpleObjectProperty<>(this, "icon");
    private final IntegerProperty numberOfNotifications = new SimpleIntegerProperty(this, "numberOfNotification",0);
    private final IntegerProperty maxNotifications = new SimpleIntegerProperty(this, "maxNotification",-1);

    private final StyleableProperty<Color> boxColor =
            new SimpleStyleableObjectProperty<>(BOX_COLOR, this, "color", Color.RED);

    private final StyleableObjectProperty<BadgeType> type =
            new SimpleStyleableObjectProperty<>(REGION_TYPE, this, "type", BadgeType.ROUND);

    // StyleablePropertyFactory
    private static final StyleablePropertyFactory<Badge> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    public Badge() {
        this(Icon.NOTIFICATION_IMPORTANT);
    }

    public Badge(Icon _icon) {
        this(new SVGIcon(_icon, 1.2), 0, 10);
    }

    public Badge(Icon _icon, int number) {
        this(new SVGIcon(_icon, 1.2), number, 10);
    }

    public Badge(Icon _icon, int number, int max) {
        this(new SVGIcon(_icon, 1.2), number, max);
    }

    public Badge(Node node, int number, int max) {
        super(node, true);
        getStyleClass().addAll(  "size-40", "max-size-40", "min-size-40");
        setCursor(Cursor.HAND);
        numberOfNotifications.set(number);
        maxNotifications.set(max);
        setAlignment(Pos.TOP_RIGHT);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new BadgeSkin(this);
    }

    private static final CssMetaData<Badge, Color> BOX_COLOR =
            FACTORY.createColorCssMetaData("-fx-box-color", s -> s.boxColor, Color.RED, false);

    private static final CssMetaData<Badge, BadgeType> REGION_TYPE =
            FACTORY.createEnumCssMetaData(
                    BadgeType.class, "-fx-type",
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

    @Contract(pure = true)
    public ObjectProperty<Icon> iconProperty() {
        return icon;
    }

    public int getMaxNotifications() {
        return maxNotifications.get();
    }

    public void setMaxNotifications(int max) {
        this.maxNotifications.set(max);
    }

    @Contract(pure = true)
    public IntegerProperty maxNotificationsProperty() {
        return maxNotifications;
    }

    public BadgeType getType() {
        return this.type.getValue();
    }

    public void setType(final BadgeType type) {
        this.type.setValue(type);
    }

    @Contract(pure = true)
    public ObjectProperty<BadgeType> typeProperty() {
        return this.type;
    }

    public Color getBoxColor() {
        return this.boxColor.getValue();
    }

    public void setBoxColor(final Color color) {
        this.boxColor.setValue(color);
    }

    @Contract(pure = true)
    public ObjectProperty<Color> boxColorProperty() {
        return (ObjectProperty<Color>) this.boxColor;
    }

    public int getNumberOfNotifications() {
        return numberOfNotifications.get();
    }

    @Contract(pure = true)
    public IntegerProperty numberOfNotificationsProperty() {
        return numberOfNotifications;
    }

    public void setNumberOfNotifications(int numberOfNotifications) {
        this.numberOfNotifications.set(numberOfNotifications);
    }

}
