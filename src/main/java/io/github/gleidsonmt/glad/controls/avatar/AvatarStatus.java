package io.github.gleidsonmt.glad.controls.avatar;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/04/2025
 */
public class AvatarStatus extends Region {

    private final AvatarView avatarView;
    private final CircleAvatarStatus circleAvatarStatus;
    private final ObjectProperty<Status> status = new SimpleObjectProperty<>(Status.BUSY);

    public AvatarStatus(Image image) {
        this.circleAvatarStatus = new CircleAvatarStatus(this);
        this.avatarView = new AvatarView(image);
        getChildren().addAll(avatarView, circleAvatarStatus);
//
        this.avatarView.heightProperty()
                .bind(this.prefHeightProperty());
        this.avatarView.heightProperty()
                .bind(this.heightProperty());
        this.avatarView.widthProperty()
                .bind(this.prefWidthProperty());
        this.avatarView.widthProperty()
                .bind(this.widthProperty());

        this.circleAvatarStatus
                .radiusProperty()
                .bind(this.widthProperty().divide(10));

//        setWidth(40);
//        setHeight(40);
    }

    @Override
    protected void layoutChildren() {
        final double width = getWidth();
        double height = getHeight();
        double top = getInsets().getTop();
        double right = getInsets().getRight();
        double left = getInsets().getLeft();
        double bottom = getInsets().getBottom();
        double contentWidth = width - left - right;
        double contentHeight = height - top - bottom;

        layoutInArea(avatarView, left, top,
                contentWidth, contentHeight,
                -1, HPos.CENTER, VPos.CENTER);
        layoutInArea(circleAvatarStatus, left, top,
                contentWidth, contentHeight,
                -1, HPos.RIGHT, VPos.BOTTOM);
    }

    @Override
    protected double computeMaxWidth(double height) {
        return avatarView.getHeight();
    }

    public void setRadius(double radius) {
        this.avatarView.setRadius(radius);
    }

    public Status getStatus() {
        return status.get();
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public void setStatus(Status status) {
        this.status.set(status);
    }
}
