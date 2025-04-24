package io.github.gleidsonmt.glad.controls.toggle_switch;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/04/2025
 */
public class LightModeSkin extends ToggleSwitchSkin {

    private SVGIcon dark = new SVGIcon(Icon.DARK_MODE_FILLED);
    private SVGIcon light = new SVGIcon(Icon.LIGHT_MODE_FILLED);

    public LightModeSkin(ToggleSwitch control) {
        super(control);
        toX = 40;
        dark.setManaged(false);
        light.setManaged(false);
        background.setWidth(70);
        background.setHeight(30);
        control.setPadding(new Insets(10));
        control.setArc(30d);
        getChildren().addAll(dark, light);
        trigger.toFront();

        control.setStyle("-fx-color-animation: -dark-gray-2;");

        dark.getPath().setStyle("-fx-fill: -warning;");
        light.getPath().setStyle("-fx-fill: -warning;");

        registerChangeListener(control.onProperty(), c -> {
            if ((boolean) c.getValue()) {
                dark.setVisible(true);
                light.setVisible(false);
            } else {
                dark.setVisible(false);
                light.setVisible(true);
            }
        });

    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);

        layoutInArea(dark, contentX+5, contentY, contentWidth, contentHeight, -1, HPos.LEFT, VPos.CENTER);
        layoutInArea(light, contentX, contentY, contentWidth - 25, contentHeight, -1, HPos.RIGHT, VPos.CENTER);
//        layoutInArea(background, contentX, contentY, contentWidth, contentHeight, -1, HPos.LEFT, VPos.CENTER);
    }
}
