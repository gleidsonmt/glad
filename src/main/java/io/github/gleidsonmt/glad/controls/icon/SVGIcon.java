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
package io.github.gleidsonmt.glad.controls.icon;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/11/2021
 */
public class SVGIcon extends SVGPathIcon {

    private String name;
    private final SimpleObjectProperty<Icon> icon = new SimpleObjectProperty<>();

    public SVGIcon() {
        this(Icon.NONE);
    }

    public SVGIcon(Icon icon, Color color) {
        this(icon, color, 1);
    }

    public SVGIcon(Icon icon) {
        this(icon,null, 1);
    }

    public SVGIcon(Icon icon, double size) {
        this(icon,null, size);
    }

    public SVGIcon(Icon icon, Color color, double size) {
        super(new SVGPath(), color, size);
        path.setContent(icon.getContent());
//        setFill(color);
        name = icon.name();

        this.icon.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setContent(newValue);
            }
        });
        setAutoSizeChildren(true);
        setMouseTransparent(true);
    }

    public Icon getIcon() {
        return icon.get();
    }

    public SimpleObjectProperty<Icon> iconProperty() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon.set(icon);
    }

    public void setContent(@NotNull Icon icon) {
        path.setContent(icon.getContent());
        if (!this.getChildren().contains(path)) {
            this.getChildren().add(path);
        }
        name = icon.name();
    }

    public String getName() {
        return name;
    }

    public SVGPath getPath() {
        return path;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SVGIconOld{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
