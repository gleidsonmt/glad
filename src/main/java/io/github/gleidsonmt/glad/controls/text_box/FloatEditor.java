

package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.enums.FloatAlignment;
import io.github.gleidsonmt.glad.controls.skin.FloatEditorSkin;
import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Skin;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  08/09/2022
 */
@DefaultProperty("children")
public class FloatEditor extends Editor {

    private final ObjectProperty<FloatAlignment> floatAlignment = new SimpleObjectProperty<>(); // top of aligment
    private final DoubleProperty distanceX = new SimpleDoubleProperty(); // distance x for the prompt label

    public FloatEditor() {
        this(null, FloatAlignment.BASELINE);
    }

    public FloatEditor(String floatPrompt, FloatAlignment alignment) {

        floatAlignment.set(alignment);

        getStyleClass().addAll("float-editor");
        setPromptText(floatPrompt);

    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new FloatEditorSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(Resources.class.getResource("agents/float-editor.css")).toExternalForm();
        return Objects.requireNonNull(Resources.getAgent("float-editor.css"));
    }

    public FloatAlignment getFloatAlignment() {
        return floatAlignment.get();
    }

    public ObjectProperty<FloatAlignment> floatAlignmentProperty() {
        return floatAlignment;
    }

    public void setFloatAlignment(FloatAlignment floatAlignment) {
        this.floatAlignment.set(floatAlignment);
    }

    public double getDistanceX() {
        return distanceX.get();
    }

    public DoubleProperty distanceXProperty() {
        return distanceX;
    }

    public void setDistanceX(double distanceX) {
        this.distanceX.set(distanceX);
    }


}
