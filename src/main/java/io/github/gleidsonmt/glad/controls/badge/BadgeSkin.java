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

import io.github.gleidsonmt.glad.GladResources;
import io.github.gleidsonmt.glad.controls.RegionType;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.CssParser;
import javafx.css.Rule;
import javafx.css.Stylesheet;
import javafx.css.converter.ColorConverter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.function.Consumer;

public class BadgeSkin extends SkinBase<Badge> {

    private final Label lblInfo = new Label();
    private final SVGIcon icon ;

    public BadgeSkin(Badge control) {
        super(control);

        icon = (SVGIcon) control.getGraphic();

        lblInfo.setAlignment(Pos.CENTER);
        lblInfo.setMinSize(20,20);
        lblInfo.setPrefSize(10,10);
        lblInfo.setMaxSize(10,10);
        lblInfo.setMouseTransparent(true);

        lblInfo.textProperty().bind(Bindings.convert(control.numberOfNotificationsProperty()));

        this.getChildren().addAll(icon, lblInfo);
        lblInfo.getStyleClass().addAll(   "text-white");

        registerChangeListener(control.boxColorProperty(), c ->
                updateBackground((Color) c.getValue(), getSkinnable().getType()));

        registerChangeListener(control.typeProperty(), c ->
                updateBackground(control.getBoxColor(), (RegionType) c.getValue()));

        updateBackground(getSkinnable().getBoxColor(), getSkinnable().getType());
    }

    private void updateBackground(Color color, RegionType type) {
        if (color != null) {
            lblInfo.setBackground(new Background(new BackgroundFill(color,
                    updateRadi(type),
                    Insets.EMPTY)));
        }
    }

    private CornerRadii updateRadi(RegionType type) {
        return type == RegionType.ROUNDED ?  new CornerRadii(3) :
                type == RegionType.RECT ? new CornerRadii(0) :
                        new CornerRadii(10);
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        layoutInArea(icon,x, y, w, h, -1, HPos.CENTER, VPos.CENTER);
        layoutInArea(lblInfo, x,y+5, getSkinnable().getWidth()-10, h, -1, HPos.RIGHT,  VPos.BASELINE);
    }

    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computeMaxHeight(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefHeight(height, topInset, rightInset, bottomInset, leftInset);
    }

}
