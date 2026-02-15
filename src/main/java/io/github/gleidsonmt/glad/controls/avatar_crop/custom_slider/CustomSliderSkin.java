package io.github.gleidsonmt.glad.controls.avatar_crop.custom_slider;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.skin.SliderSkin;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/11/2024
 */
public class CustomSliderSkin  extends SliderSkin {

    Region region = new Region();
    private double width = 0;
    private Label label = new Label("15");
    Region thumb;

    public CustomSliderSkin(CustomSlider control) {
        super(control);

        region.setStyle("-fx-background-color: -track-color; -fx-background-radius: 100px;");
        region.setMinHeight(1);
        region.setMaxWidth(0);
        region.setMouseTransparent(true);

        getChildren().add(  1, region);
        getChildren().add(  label);
//        label.setManaged(false);
        label.toFront();

        thumb = (Region) control.lookup(".thumb");

        label.setVisible(false);
        control.setOnMousePressed(e -> {
//            control.requestLayout();
            control.requestLayout();
            this.width = e.getX();
            label.relocate(e.getX(), e.getY());
            label.setVisible(true);
        });

        control.setOnMouseReleased(e -> {
            label.setVisible(false);
        });

        control.setOnMouseDragged(e -> {
//            System.out.println("e.getX() = " + e.getX());
//            System.out.println("thumb = " + thumb.getLayoutX());
//            System.out.println("thumb.getWidth() = " + thumb.getWidth());
//            if (e.getX() > thumb.getWidth()) return;
            this.width = thumb.getBoundsInParent().getMinX();
            control.requestLayout();
        });

        registerChangeListener(control.valueProperty(), e -> {
            // only animate thumb if the track was clicked - not if the thumb is dragged
//              this.width = thumb.getBoundsInParent().getMinX();
//            control.requestLayout();

            Number x = (Number) e.getValue();

            label.setText(String.format("%.2f", x.floatValue()));
//            region.setMaxWidth(thumb.getLayoutX());
//            region.setPrefWidth(thumb.getLayoutX());
//            region.setMinWidth(thumb.getLayoutX());
//            region.setLayoutX(thumb.getLayoutX());
//            System.out.println("this.getSkinnable().lookup(\".thumb\") = " + this.getSkinnable().lookup(".thumb"));
        });
//        region.minWidthProperty().bind(thumb.translateXProperty());
//        positionInArea(trackClicked);
    }

//    @Override
//    protected void layoutInArea(Node child, double areaX, double areaY, double areaWidth, double areaHeight, double areaBaselineOffset, HPos halignment, VPos valignment) {
//        super.layoutInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, halignment, valignment);
//        layoutInArea(region, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, HPos.CENTER, VPos.TOP);
//    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
       super.layoutChildren(x, y, w, h);
        region.relocate(5, h/2 -3);
        region.resize( width, 5);
//        label.resize( width, 5);
        layoutInArea(label, thumb.getBoundsInParent().getMinX() + 20, y - 30, w, h, 0, HPos.LEFT, VPos.CENTER);
//        label.relocate(x, y);

//        layoutInArea(region, x, y, 100, h, 0, HPos.LEFT, VPos.CENTER);
    }

}
