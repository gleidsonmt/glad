package io.github.gleidsonmt.glad.controls.loaders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/09/2024
 */
@SuppressWarnings("unused")
public class Suspense3DCircle extends CircleLoader implements SuspenseLoader {
    public Suspense3DCircle() {
        super();
        this.getStyleClass().add("DCircle");
    }

    public Suspense3DCircle(String _title, String _legend) {
        super(_title, _legend);
        this.getStyleClass().add("DCircle");
    }

    @Override
    protected StackPane createCircleContainer() {
        StackPane circleContainer = new StackPane();

        ObservableList<Circle> circles = FXCollections.observableArrayList();

        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle();
            circle.getStyleClass().add("track-circle");
            circle.setStyle(" -fx-stroke-dash-array : 10;");
            circle.setStrokeWidth(2);
            circle.setRadius(120);
            circle.setFill(Color.TRANSPARENT);
            circles.add(circle);
        }

        rotate(circles.get(0), 360, new Point3D(100, 0, 0));
        rotate(circles.get(1), 180, new Point3D(100, 100, 0));
        rotate(circles.get(2), 270, new Point3D(100, 50, 0));
        rotate(circles.get(3), 90, new Point3D(100, 100, 0));

        circleContainer.getChildren().setAll(circles);
        return circleContainer;
    }
}
