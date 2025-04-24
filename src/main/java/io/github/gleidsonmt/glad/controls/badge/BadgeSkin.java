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

import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

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

        lblInfo.setCache(true);
        lblInfo.setCacheHint(CacheHint.QUALITY);

        lblInfo.getStyleClass().add("bold");
//        lblInfo.getStyleClass().add("font-instagram-headline");
//        lblInfo.setStyle("-fx-font-family: \"Instagram Sans Headline\"");

        StringConverter<Number> converter = new StringConverter<>() {
            @Override
            public String toString(Number number) {
                if (number.intValue() >= control.getMaxNotifications()) {
                    return "9+";
                } else {
                    return String.valueOf(number.intValue());
                }
            }

            @Override
            public Number fromString(String s) {
                return null;
            }
        };

        Bindings.bindBidirectional(lblInfo.textProperty(), control.numberOfNotificationsProperty(), converter);

        this.getChildren().addAll(icon, lblInfo);
        lblInfo.getStyleClass().addAll(   "text-white");

        registerChangeListener(control.boxColorProperty(), c ->
                updateBackground((Color) c.getValue(), getSkinnable().getType()));

        registerChangeListener(control.typeProperty(), c ->
                updateBackground(control.getBoxColor(), (BadgeType) c.getValue()));

        updateBackground(getSkinnable().getBoxColor(), getSkinnable().getType());
    }

    private void updateBackground(Color color, BadgeType type) {
        if (color != null) {
            lblInfo.setBackground(new Background(new BackgroundFill(color,
                    updateRadi(type),
                    Insets.EMPTY)));
        }
    }

    private CornerRadii updateRadi(BadgeType type) {
        return type == BadgeType.ROUNDED ?  new CornerRadii(3) :
                type == BadgeType.RECT ? new CornerRadii(0) :
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
