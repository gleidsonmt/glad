package io.github.gleidsonmt.glad.controls.avatar;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
public class StackedAvatar extends Group {

    @Deprecated
    public StackedAvatar(int max, Image... images) {
        this(max, 20, images);
    }

    @Deprecated
    public StackedAvatar(int max, double radius, Image... images) {
        int translate = 0;
        for (int i = 0; i < Math.min(images.length, max); i++) {
            AvatarView avatar = new AvatarView(images[i], radius *2, radius*2);
            avatar.setRadius(radius);
            avatar.setLayoutX(translate+=25);
            this.getChildren().add(avatar);
        }
        if (images.length - 3 > 0) {
            Label label = new Label(images.length - 3 + "+");
            label.setAlignment(Pos.CENTER);
            label.setPrefSize(radius *2, radius *2);

            label.getStyleClass().addAll("display-center","border-white", "border-2", "bg-accent", "bg-insets-1", "text-white", "padding-5", "round", "bold");
            label.setLayoutX(translate+(radius/ 2));
            label.setLayoutY(-(radius));
            this.getChildren().add(label);
        }
    }

    public StackedAvatar(int max, double size, AvatarView... avatarViews) {
        int translate = 0;
        for (int i = 0; i < Math.min(avatarViews.length, max); i++) {
            avatarViews[i].setLayoutX(translate+=25);
            avatarViews[i].setSize(size);
            this.getChildren().add(avatarViews[i]);
        }
        if (avatarViews.length - 3 > 0) {
            Label label = new Label(avatarViews.length - 3 + "+");
            label.setAlignment(Pos.CENTER);
            label.setPrefSize(size , size);

            label.getStyleClass().addAll("display-center","border-white", "border-2", "bg-accent", "bg-insets-1", "text-white", "padding-5", "round", "bold");
            label.setLayoutX(translate+(size/ 2));
            this.getChildren().add(label);
        }
    }
}
