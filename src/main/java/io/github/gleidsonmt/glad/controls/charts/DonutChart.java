package io.github.gleidsonmt.glad.controls.charts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class DonutChart extends PieChart {

    private final Circle innerCircle;

    public DonutChart() {
        this(FXCollections.<Data>observableArrayList());
    }

    public DonutChart(ObservableList<Data> pieData) {
        super(pieData);

        this.getStyleClass().add("donut-chart");
        innerCircle = new Circle();
        innerCircle.getStyleClass().add("inner-circle");
        Circle clip = new Circle();

        // just styled in code for demo purposes,
        // use a style class instead to style via css.
//        innerCircle.setFill(Color.WHITE);
        innerCircle.setStyle("-fx-fill: -fx-background;");
        innerCircle.setStroke(Color.TRANSPARENT);

        innerCircle.setStrokeWidth(3);

//        this.setClip(clip);
    }

    @Override
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);

        addInnerCircleIfNotPresent();
        updateInnerCircleLayout();

    }

    private void addInnerCircleIfNotPresent() {
        if (!getData().isEmpty()) {
            Node pie = getData().getFirst().getNode();
            if (pie.getParent() instanceof Pane parent) {

                if (!parent.getChildren().contains(innerCircle)) {
                    parent.getChildren().add(innerCircle);
                }
            }
        }
    }

    private void updateInnerCircleLayout() {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (Data data: getData()) {
            Node node = data.getNode();

            Bounds bounds = node.getBoundsInParent();
            if (bounds.getMinX() < minX) {
                minX = bounds.getMinX();
            }
            if (bounds.getMinY() < minY) {
                minY = bounds.getMinY();
            }
            if (bounds.getMaxX() > maxX) {
                maxX = bounds.getMaxX();
            }
            if (bounds.getMaxY() > maxY) {
                maxY = bounds.getMaxY();
            }
        }

        innerCircle.setCenterX(minX + (maxX - minX) / 2);
        innerCircle.setCenterY(minY + (maxY - minY) / 2);

        innerCircle.setRadius((maxX - minX) / 3);
    }
}