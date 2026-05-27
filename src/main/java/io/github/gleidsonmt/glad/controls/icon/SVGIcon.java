
package io.github.gleidsonmt.glad.controls.icon;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  23/11/2021
 */
public class SVGIcon extends SVGPathIcon {

    private String name;
    private final SimpleObjectProperty<Icon> icon = new SimpleObjectProperty<>();

    public SVGIcon() {
        this(Icon.NONE);
    }

    public SVGIcon(Icon icon, Color color) {
        this(icon, color, 1);
    }

    public SVGIcon(Icon icon) {
        this(icon, null, 1);
    }

    public SVGIcon(Icon icon, double size) {
        this(icon, null, size);
    }

    public SVGIcon(Icon icon, Color color, double size) {
        super(new SVGPath(), color, size);
        path.setContent(icon.getContent());
//        setFill(color);
        name = icon.name();

        this.icon.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setContent(newValue);
            }
        });
        setPickOnBounds(true);
        setAutoSizeChildren(true);
        setMouseTransparent(true);
    }

    public Icon getIcon() {
        return icon.get();
    }

    public SimpleObjectProperty<Icon> iconProperty() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon.set(icon);
    }

    public void setContent(@NotNull Icon icon) {
        path.setContent(icon.getContent());
        if (!this.getChildren().contains(path)) {
            this.getChildren().add(path);
        }
        name = icon.name();
    }

    public String getName() {
        return name;
    }

    public SVGPath getPath() {
        return path;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SVGIcon{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
