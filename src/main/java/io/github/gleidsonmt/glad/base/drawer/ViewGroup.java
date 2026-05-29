package io.github.gleidsonmt.glad.base.drawer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on  10/06/2025
 */
@ApiStatus.Experimental
public class ViewGroup extends Module {

    private final ObservableList<Module> modules;

    public ViewGroup(String name, Module... _modules) {
        this(name, null, _modules);
    }

    public ViewGroup(String name, Node icon, Module... _modules) {
        super(name, icon);
        this.modules = FXCollections.observableArrayList(_modules);
        transformInGroup(_modules).forEach(el -> el.setParent(this));


        modules.addListener((ListChangeListener<Module>) change -> {
            if (change.next()) {
                transformInGroup((Module) modules).forEach(el -> el.setParent(ViewGroup.this));
            }
        });
    }

    private Stream<Module> transformInGroup(Module... _modules) {
        return Arrays.stream(_modules)
                .filter(Objects::nonNull);
    }

    public ObservableList<Module> getModules() {
        return modules;
    }

    @Override
    public String toString() {
        return getName();
    }
}
