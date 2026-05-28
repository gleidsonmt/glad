package io.github.gleidsonmt.glad.controls.form;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.jetbrains.annotations.ApiStatus;

import java.util.Objects;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 27/05/2026
 */
@ApiStatus.Experimental
public abstract class AbstractForm<T> extends GridPane implements Form<T> {

    private final BooleanProperty valid = new SimpleBooleanProperty(true);

    private final T model;
    private final ObservableList<FormField> fields;

    public AbstractForm(T model) {
        this.model = model;
        fields = FXCollections.observableArrayList();
    }

    public void render() {

        BooleanBinding allFieldsValid = Bindings.createBooleanBinding(
                () -> fields.stream()
                        .filter(Objects::nonNull)
                        .allMatch(FormField::isValid),
                fields.stream()
                        .filter(Objects::nonNull)
                        .map(FormField::validProperty)
                        .toArray(Observable[]::new)
        );

        valid.bind(allFieldsValid);

    }

    public ObservableList<FormField> getFields() {
        return fields;
    }

    public void addField(FormField field) {
        fields.add(field);
        if (field instanceof Node node) {
            getChildren().add(node);
            GridPane.setHgrow(node, Priority.ALWAYS);
        }
    }

    public void addField(FormField field, int colIndex, int rowIndex) {
        fields.add(field);
        if (field instanceof Node node) {
            add(node, colIndex, rowIndex);
            GridPane.setHgrow(node, Priority.ALWAYS);
        }
    }

    public void addLabel(Label label,int colIndex, int rowIndex) {
        add(label, colIndex, rowIndex);
    }

    @Override
    public T get() {
        return this.model;
    }

    @Override
    public boolean validate() {
        fields.stream().filter(Objects::nonNull)
                .forEach(FormField::validate);

        return valid.get();
    }

    @Override
    public abstract void persist();
}
