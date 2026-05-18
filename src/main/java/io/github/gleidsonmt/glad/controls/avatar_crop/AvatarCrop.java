

package io.github.gleidsonmt.glad.controls.avatar_crop;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.avatar_crop.footer.Footer;
import io.github.gleidsonmt.glad.controls.avatar_crop.header.Header;
import io.github.gleidsonmt.glad.controls.avatar_crop.image_container.ImageContainer;
import io.github.gleidsonmt.glad.controls.avatar_crop.image_container.ScrollView;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  14/11/2024
 */
public class AvatarCrop extends Region {

    private final ImageContainer imageContainer;
    private final Header header;
    private CropAction onSave;
    private CloseAction onClose;

    private final VBox container = new VBox();
    private final StringProperty imageFormat = new SimpleStringProperty("png");

    public AvatarCrop(Image image) {
        this.setId("avatar-crop-container");
        this.container.setSpacing(10);
        this.container.setAlignment(Pos.CENTER);

        header = new Header(Icon.RE_CENTER, "Crop and adjust image position");
        imageContainer = new ImageContainer(image);
        Footer footer = new Footer(imageContainer);
        setPadding(new Insets(20));

        this.container.getChildren().addAll(header, imageContainer, footer);

        this.getChildren().setAll(container);

        footer.getButtonBar().getSaveButton().setOnAction(e -> {
            if (onSave != null) onSave.crop(createImage(imageContainer.getScrollView()));
        });

        footer.getButtonBar().getCancelButton().setOnAction(e -> {
            if (onClose != null) onClose.close();
        });

        getStylesheets().add(Resources.getCss("avatar-crop.css"));
    }

    private WritableImage createImage(ScrollView content) {
        WritableImage wImage = new WritableImage((int) content.getWidth(), (int) content.getWidth());
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        snapshotParameters.setViewport(new Rectangle2D(0, 0, 500, 500));
        return content.snapshot(snapshotParameters, wImage);
    }

    public void setOnSave(CropAction action) {
        this.onSave = action;
    }

    public void setOnClose(CloseAction action) {
        this.onClose = action;
        header.setOnClose(action);
    }

    public ObjectProperty<BorderClip> borderClipProperty() {
        return imageContainer.borderClipProperty();
    }

    public void setBorderClip(BorderClip clip) {
        this.imageContainer.setBorderClip(clip);
    }

    public String getImageFormat() {
        return imageFormat.get();
    }

    public StringProperty imageFormatProperty() {
        return imageFormat;
    }

    @Override
    public void layoutChildren() {
        super.layoutChildren();
        container.resize(
                this.getWidth(),
                this.getHeight()
        );
    }
}
