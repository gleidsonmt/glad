

package io.github.gleidsonmt.glad.controls.loaders;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  16/03/2026
 */
public class AtomicLoader extends StackPane {

    private AnchorPane container = new AnchorPane();

    public AtomicLoader() {
//        this.setStyle("-fx-background-color: red");
        this.setMaxSize(500, 500);
        this.setMinSize(500, 500);
        SVGPath pathOne = createSVGPath();
        pathOne.setRotate(0);
        SVGPath pathTwo = createSVGPath();
        pathTwo.setRotate(-60);
        SVGPath pathThree = createSVGPath();
        pathThree.setRotate(60);

        Circle circleOne = createCircle();
        Circle circleTwo = createCircle();
        Circle circleThree = createCircle();

        Group group = new Group();
        group.setAutoSizeChildren(false);

        Circle circle = createCircle();
        circle.setStroke(Color.TRANSPARENT);
        circle.setRadius(30);
        circle.setFill(Color.web("#007deb"));

//        Rectangle rectangle = new Rectangle();
//        rectangle.setArcWidth(50);
//        rectangle.setArcHeight(50);
//        rectangle.setWidth(40);
//        rectangle.setHeight(10);
//        rectangle.setFill(Color.web("#c9d1d9"));

//        pathOne.setLayoutY(80);
        group.getChildren().addAll(pathOne, circleOne, pathTwo, circleTwo, pathThree, circleThree);
//        this.getChildren().addAll(pathOne, circleOne);
        container.getChildren().add(group);
        this.getChildren().addAll(container, circle);
//        this.setAlignment(Pos.CENTER);

//        AnchorPane.layoutInArea(group, 80, 80,
//                500, 500, 0, Insets.EMPTY, false, false, HPos.LEFT,  VPos.TOP, true);
        AnchorPane.positionInArea(group, 145, 145,
                205, 205, 0, Insets.EMPTY, HPos.CENTER, VPos.CENTER, true);
//        AnchorPane.setTopAnchor(group, 200D);
//        AnchorPane.setLeftAnchor(circleTwo, 150D);
//        StackPane.setAlignment(circleTwo, Pos.CENTER_LEFT);
//        rectangle.setClip(pathThree);
//        circleOne.setManaged(false);

        rotate(pathOne, circleOne, 1200);
        rotate(pathTwo, circleTwo, 3600);
        rotate(pathThree, circleThree, 3800);

//        rotate(pathOne, circleOne, 1200);

//       rectangle.setTranslateX(20);
//        rotate(pathThree, rectangle, 3600);


//        pathTwo.setStyle("-fx-stroke-dash-array: 18; -fx-stroke-dash-offset: 20");

//       circleThree.setCenterX(450);

//        circleThree.setRotationAxis(new Point3D(100, 100, 100));
//        circleThree.set(100);
        rotate(group);
    }

    protected void rotate(Node node) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(10), this);

//        rotate.setAutoReverse(true);

        rotate.setByAngle(360);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.play();


    }

    private void rotate(SVGPath path, Node circle, double from) {
        PathTransition transition = new PathTransition(Duration.seconds(5.5), path, circle);

        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(-1);
//        transition.jumpTo(Duration.seconds(cycle));
//        transition.playFrom("M 107 97");
//        transition.setInterpolator(Interpolator.TANGENT(Duration.millis(200), 100, Duration.millis(100), 400));
//        transition.setInterpolator(Interpolator.SPLINE(0.10,0.63,0.13,0.1));
        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setInterpolator(Interpolator.LINEAR);
//        transition.setDelay(Duration.millis(1500));
        transition.playFrom(Duration.millis(from));

//        Timeline timeline = new Timeline();
//        double speed = 14000;
//        timeline.getKeyFrames().setAll(
//                new KeyFrame(Duration.ZERO, new KeyValue(
//                        path.strokeDashOffsetProperty(), 0
//                )),
//                new KeyFrame(Duration.millis(speed), new KeyValue(
//                        path.strokeDashOffsetProperty(), -90
//                ))
////                new KeyFrame(Duration.ZERO, new KeyValue(
////                        path.strokeDashOffsetProperty(), path.getStrokeDashOffset()
////                )),
////                new KeyFrame(Duration.millis(speed), new KeyValue(
////                        path.strokeDashOffsetProperty(), -320
////                ))
//        );
//        timeline.setDelay(Duration.millis(from));
//
////        path.setStrokeDashOffset(0);
//        path.getStrokeDashArray().setAll(35D);
//
////            timeline.setAutoReverse(true);
//        timeline.setCycleCount(-1);
////        timeline.setRate(1);
////        timeline.playFrom(Duration.millis(from));
//
//        timeline.play();
    }

    private SVGPath createSVGPath() {
        SVGPath path = new SVGPath();
        path.setContent("M 107 97 C 107 99 108 104 111 104 C 114 104 115 100 115 97 C 115 94 114 90 111 90 C 108 90 107 93 107 97");
        path.setFill(Color.TRANSPARENT);
        path.setStrokeWidth(0.2);
        path.setStroke(Color.web("#007deb"));
        path.setScaleX(20);
        path.setScaleY(25);
        return path;
    }

    private Circle createCircle() {
        Circle circle = new Circle();
        circle.setStrokeWidth(2);
        circle.setRadius(10);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.web("#007deb"));
        return circle;
    }
}

