package io.github.gleidsonmt.glad.controls.avatar;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
@SuppressWarnings("unused")
public class StackedAvatar extends Group {

    private IntegerProperty max;
    private DoubleProperty avatarSize;
    private DoubleProperty avatarRadius;
    private ObservableList<AvatarView> avatarViews;
    private IntegerProperty spacing = new SimpleIntegerProperty(0);

    private Label maxLabel;

    public StackedAvatar() {
        this.max = new SimpleIntegerProperty(-1);
        this.avatarSize = new SimpleDoubleProperty(40);
        this.avatarRadius = new SimpleDoubleProperty(40);
        this.avatarViews = FXCollections.observableArrayList();
        maxLabel = createLabelMax();

       this.maxProperty().addListener((_, oldVal, newVal) -> {
           if (oldVal.intValue() == -1) {
               return;
           }

           if (newVal.intValue() > oldVal.intValue()) {
               int spaceAvailable = newVal.intValue() - oldVal.intValue();
               int index = getSize();
               List<AvatarView> avatars = avatarViews.subList(index, spaceAvailable + index);
               addAvatarViews(index, avatars);
           } else {
               int spaceAvailable =  oldVal.intValue() - newVal.intValue();
               int index = getSize();
               getChildren().remove(index-spaceAvailable, index);
               updateLabelMax();
           }
       });

       this.avatarSizeProperty().addListener((_, _, _) -> resize());
       this.avatarRadiusProperty().addListener((_, _, _) -> resize());

        this.avatarViews.addListener((ListChangeListener<AvatarView>) change -> {
            if (change.next()) {

                if (change.wasReplaced()) {
                    for (AvatarView av : change.getAddedSubList()) {
                        getChildren().remove(change.getFrom(), change.getTo());
                        getChildren().addAll(change.getFrom(), change.getAddedSubList());
                        av.setLayoutX(change.getTo() * getTranslate());
                    }
                    return;
                }

                if (change.wasAdded()) {
                    addAvatarViews(change.getFrom(), change.getAddedSubList());
                }

                if (change.wasRemoved()) {
                    getChildren().removeAll(change.getRemoved());
                    updateLabelMax();
                }

            }
        });
    }


    private void addAvatarViews(int in, List<? extends AvatarView> avatars) {

        int spaceAvailable = max.get() - getSize();
        int totalCanBeAdded = Math.min(avatars.size(), spaceAvailable);


        for (int i = 0; i < totalCanBeAdded; i++) {
            avatars.get(i).setSize(avatarSize.get());
            getChildren().add(in, avatars.get(i));
            avatars.get(i).setRadius(avatarRadius.get() );
            avatars.get(i).setLayoutX(in++ * getTranslate());
        }
        updateLabelMax();
    }

    private int getSize() {
        if (getChildren().contains(maxLabel)) {
            return getChildren().size() - 1;
        } else return getChildren().size();
    }

    private void updateLabelMax() {
        int diff = avatarViews.size() - max.get();
        maxLabel.setText(diff + "+");

        if (diff > 0) {
            if (!getChildren().contains(maxLabel)) {
                getChildren().addLast(maxLabel);
            } else {
                maxLabel.toFront();
            }
            maxLabel.setLayoutX(getChildren().indexOf(maxLabel) * getTranslate());
        } else {
            getChildren().remove(maxLabel);
        }
    }

    private Label createLabelMax() {
        Label label = new Label("99+");
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(avatarSize.get(), avatarSize.get());

        label.getStyleClass().addAll("display-center", "border-white", "border-2", "bg-accent", "bg-insets-1", "text-white", "padding-5", "bold");

        label.setStyle("-fx-font-size: " + avatarSize.get() / 3 +
                       "; -fx-background-radius: " + avatarRadius.get()/2 +
                       "; -fx-border-radius: " + avatarRadius.get() / 2);
        return label;
    }

    private void resize() {
        getChildren()
                .stream()
                .filter(node -> node instanceof AvatarView)
                .map(node -> (AvatarView) node)
                .forEach(node -> {
                    node.setSize(avatarSize.get());
                    node.setRadius(avatarRadius.get() );
                    node.setLayoutX(getChildren().indexOf(node) * getTranslate());
                });
        maxLabel.setStyle("-fx-font-size: " + avatarSize.get() / 3 + ";");
        maxLabel.setPrefSize(avatarSize.get(), avatarSize.get());
        maxLabel.setLayoutX(getChildren().indexOf(maxLabel) * getTranslate());
        maxLabel.setStyle("-fx-font-size: " + avatarSize.get() / 3 +
                       "; -fx-background-radius: " + avatarRadius.get()/2 +
                       "; -fx-border-radius: " + avatarRadius.get() / 2);
    }

    private double getTranslate() {
        return (avatarSize.get() + spacing.get()) / 2;
    }

    public int getSpacing() {
        return spacing.get();
    }

    public IntegerProperty spacingProperty() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing.set(spacing);
    }

    @Deprecated(forRemoval = true)
    public StackedAvatar(int max, double size, AvatarView... avatarViews) {
        int translate = 0;
        for (int i = 0; i < Math.min(avatarViews.length, max); i++) {
            avatarViews[i].setLayoutX(translate += 25);
            avatarViews[i].setSize(size);
            this.getChildren().add(avatarViews[i]);
        }
        if (avatarViews.length - 3 > 0) {
            Label label = new Label(avatarViews.length - 3 + "+");
            label.setAlignment(Pos.CENTER);
            label.setPrefSize(size, size);

            label.getStyleClass().addAll("display-center", "border-white", "border-2", "bg-accent", "bg-insets-1", "text-white", "padding-5", "round", "bold");
            label.setLayoutX(translate + (size / 2));
            this.getChildren().add(label);
        }
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public double getAvatarSize() {
        return avatarSize.get();
    }

    public DoubleProperty avatarSizeProperty() {
        return avatarSize;
    }

    public void setAvatarSize(double size) {
        this.avatarSize.set(size);
    }

    public ObservableList<AvatarView> getAvatarViews() {
        return avatarViews;
    }

    public double getAvatarRadius() {
        return avatarRadius.get();
    }

    public DoubleProperty avatarRadiusProperty() {
        return avatarRadius;
    }

    public void setAvatarRadius(double avatarRadius) {
        this.avatarRadius.set(avatarRadius);
    }

}
