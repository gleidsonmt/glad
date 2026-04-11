package io.github.gleidsonmt.glad.base.drawer;

import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.ModuleView;
import io.github.gleidsonmt.glad.base.View;
import io.github.gleidsonmt.glad.drawer.DrawerItem;
import io.github.gleidsonmt.glad.drawer.DrawerMenu;
import io.github.gleidsonmt.glad.drawer.DrawerSeparator;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/07/2024
 * Revised 2
 */
@ApiStatus.Experimental
public class Drawer extends VBox {

    private final ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();
    private ObjectProperty<Callback<Module, Node>> cellFactory;
    private ObjectProperty<ObservableList<Module>> items;

    private final VBox searchBox = new VBox();
    private final VBox defaultBox = new VBox();

    protected final DrawerContainer container;
    protected final ToggleGroup group = new ToggleGroup();

    public Drawer() {
        this(FXCollections.observableArrayList());
    }

    public Drawer(@NotNull Module... modules) {
        this(FXCollections.observableArrayList(modules));
    }

    public Drawer(@NotNull ObservableList<Module> modules) {
        this(modules, module -> switch (module) {
            case View view -> new DrawerItem(view);
            case ModuleSeparator separator -> new DrawerSeparator(separator);
            case ModuleView menu -> new DrawerMenu(menu); // TitledPane
            case null, default -> {
                assert module != null;
                yield new DrawerItem(module);
            }
        });
    }

    public Drawer(@NotNull ObservableList<Module> modules, @NotNull Callback<Module, Node> cellFactory) {
        this.setId("drawer");
        this.container = new DrawerContainer(defaultBox);
        defaultBox.setId("drawer-content");

        itemsProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                defaultBox.getChildren().clear();
                Objects.requireNonNull(newValue).forEach(this::recursivePopulate);

                newValue.addListener((ListChangeListener<Module>) c -> {
                    if (c.next()) {
                        if (c.wasAdded()) {
                            c.getAddedSubList().forEach(Drawer.this::recursivePopulate);
                        } else if (c.wasRemoved()) {
                            c.getRemoved().forEach(module ->
                                    defaultBox.getChildren().remove(findNode(defaultBox.getChildren(), module)));
                        }
                    }
                });
            }
        });

        cellFactoryProperty().set(cellFactory);
        itemsProperty().set(modules);

        this.getChildren().addAll(container);
        setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(container, Priority.ALWAYS);
        this.setPrefWidth(250);

//
//        currentModule.addListener((_, _, newValue) -> group.getToggles().forEach(e -> {
//            if (e.getUserData() == newValue) {
//                group.selectToggle(e);
//            }
//        }));
//
        group.selectedToggleProperty().addListener((_, oldValue, newValue) -> {
            if (newValue != null) {
                r((ToggleButton) newValue, true);
            }
            if (oldValue != null) {
                Node oldParent = getRoot(((ToggleButton) oldValue));
                Node newParent = getRoot(((ToggleButton) newValue));
                if (oldParent != newParent) {
                    r((ToggleButton) oldValue, false);
                    Platform.runLater(() -> {
                        if (newParent instanceof DrawerMenu parent) {
                            parent.setExpanded(true);
                        }
                        if (oldParent instanceof DrawerMenu p) {
                            p.setExpanded(false);
                        }
                    });
                }
            }
        });
//
//        Platform.runLater(() -> {
//testing
//        if (!group.getToggles().isEmpty()) {
            currentModule.bind(group.selectedToggleProperty().map(e -> (Module) e.getUserData()));
//            group.selectToggle(group.getToggles().getFirst());
//            currentModule.setValue((ModuleView) group.getToggles().getFirst().getUserData());
//        }
//        });

    }

    public void select(Module module) {
        group.selectToggle(group.getToggles().stream().filter(e -> e.getUserData() == module).findFirst().orElse(null));
    }

    public void selectFirst() {
        group.selectToggle(group.getToggles().getFirst());
    }

    public void selectLast() {
        group.selectToggle(group.getToggles().getLast());
    }

    public void selectNext() {
        group.selectToggle(group.getToggles().get(group.getToggles().indexOf(group.getSelectedToggle()) + 1));
    }

    public void selectPrevious() {
        group.selectToggle(group.getToggles().get(group.getToggles().indexOf(group.getSelectedToggle()) - 1));
    }

    public void select(int index) {
        group.selectToggle(group.getToggles().get(index));
    }

    public Toggle getSelected() {
        return group.getSelectedToggle();
    }

    public ObservableList<Toggle> getToggles() {
        return group.getToggles();
    }

    public void setSearchable(StringProperty property, Predicate<String> predicate) {
        property.addListener((_, _, newVal) -> {
            if (!newVal.isEmpty()) {
                container.setContainer(searchBox);
                searchBox.getChildren().clear();

                find(predicate)
                        .forEach(e -> {
                            Optional<BoxModule> opt = findModuleInSearchBox(e); // verify if a box has e
                            if (opt.isEmpty() && e.getParent() != null) { // if the box doesn't have a module and module has a parent
                                // add a new BoxModule with module
                                searchBox.getChildren().add(new BoxModule(e.getParent().getName(), createToggle(e)));
                            } else if (e.getParent() != null && opt.isPresent()) { // if module already in search box and module has a parent
                                // find, and add to a BoxModule already in the search box.
                                getBoxModule(e).ifPresent(boxModule -> boxModule.getChildren().add(createToggle(e)));
                            }
                        });
            } else {
                container.setContainer(defaultBox);
                VBox.setVgrow(container, Priority.ALWAYS);
            }
        });
    }

    /**
     * If drawer is in mode of search, find a module.
     * @param module The module in the SearchBox.
     * @return The BoxModule (VBox) that is equal to the module name.
     */
    private @NotNull Optional<BoxModule> findModuleInSearchBox(Module module) {
        return searchBox
                .getChildren()
                .stream()
                .filter(el -> el instanceof BoxModule)
                .map(el -> (BoxModule) el)
                .filter(_ -> module.getParent() != null)
                .filter(el -> el.getName().equals(module.getParent().getName()))
                .findAny();
    }

    /**
     * If drawer is in mode of search, find a a bom module.
     * @param e The module in the SearchBox.
     * @return The BoxModule (VBox) that is equal to the module name.
     */
    private @NotNull Optional<BoxModule> getBoxModule(Module e) {
        return searchBox.getChildren()
                .stream()
                .filter(el -> el instanceof BoxModule)
                .map(el -> (BoxModule) el)
                .filter(el -> e.getParent().getName().equals(el.getName()))
                .findAny();
    }

    private @NotNull List<Module> find(Predicate<String> predicate) {
        List<Module> findedList = new ArrayList<>();
        _find(Objects.requireNonNull(getItems()), findedList, predicate);
        return findedList;
    }

    private @Nullable Module _find(@NotNull ObservableList<Module> modules, List<Module> findedList, Predicate<String> predicate) {
        for (Module mod : modules) {
            if (predicate.test(mod.getName().toLowerCase())) {
                findedList.add(mod);
            } else {
                if (!(mod instanceof ModuleSeparator) && mod.getModules() != null && !mod.getModules().isEmpty()) {
                    Module module = _find(mod.getModules(), findedList, predicate);
                    if (module != null) {
                        findedList.add(module);
                    }
                }
            }
        }
        return null;
    }

    public @Nullable Module find(String name) {
        return find(Objects.requireNonNull(getItems()), name);
    }

    private @Nullable Module find(@NotNull List<Module> modules, String name) {
        for (Module mod : modules) {
            if (!mod.getName().equals(name)) {
                if (!(mod instanceof ModuleSeparator)) {
                    if (mod.getModules() != null && !mod.getModules().isEmpty()) {
                        Module moduleImpl = find(mod.getModules(), name);
                        if (moduleImpl != null) return moduleImpl;
                    }
                }
            } else {
                return mod;
            }
        }
        return null;
    }

    private void update(Node titledPane, boolean active) {
        if (active) {
            if (!titledPane.getStyleClass().contains("module-selected")) {
                titledPane.getStyleClass().addAll("module-selected");
            }
        } else {
            titledPane.getStyleClass().removeAll("module-selected");
        }
    }

    public void setHeader(Node node) {
        this.getChildren().addFirst(node);
    }

    public void setFooter(Node node) {
        this.getChildren().addLast(node);
    }

    private Node getRoot(Node module) {
        if (module != null && module.getStyleClass().contains("module-first")) {
            return module;
        } else {
            if (module != null) return getRoot(module.getParent());
        }
        return null;
    }

    private void r(Node module, boolean active) {
        if (module instanceof TitledPane titledPane && titledPane.getStyleClass().contains("module-first")) {
            update(titledPane, active);
        } else {
            if (module.getParent() == null) return;
            r(module.getParent(), active);
        }
    }

    private Node findNode(List<Node> nodes, Module module) {
        for (Node node : nodes) {
            if (node.getUserData() == module) {
                return node;
            } else {
                if (node instanceof Pane pane) {
                    if (pane.getChildren() != null) {
                        Node found = findNode(pane.getChildren(), module);
                        if (found != null) return found;
                    }

                }
            }
        }
        return null;
    }

    public ToggleButton createToggle(Module module) {
        var item = new DrawerItem(module);
        group.getToggles().add(item);
        item.setOnMouseClicked(_ -> currentModule.set(module));
        return item;
    }

    private Node call(Module module) {
        return cellFactoryProperty().get().call(module);
    }

    public void recursivePopulate(Module moduleImpl) {
        if (moduleImpl instanceof View view) {

            ToggleButton b = (ToggleButton) call(view);
            group.getToggles().add(b);
            b.setOnMouseClicked(_ -> currentModule.set(moduleImpl));
            moduleImpl.setNode(b);

            if (view.getContainer() != null) {
                view.getContainer().getChildren().add(b);
            } else {
                defaultBox.getChildren().add(b);
            }
        } else if (moduleImpl instanceof ModuleSeparator moduleSeparator) {
            defaultBox.getChildren().add(call(moduleSeparator));

        } else if (moduleImpl instanceof ModuleView moduleView) {
            TitledPane container = (TitledPane) call(moduleImpl);

            if (moduleView.getContainer() == null) {
                container.getStyleClass().add("module-first");
                defaultBox.getChildren().add(container);

                if (moduleView.getModules() != null && !moduleView.getModules().isEmpty()) {
                    moduleView.getModules().forEach(el -> {
                        el.setContainer((Pane) container.getContent());
                        recursivePopulate(el);
                    });
                }
            } else {
                moduleView.getContainer().getChildren().add(container);
                moduleView.getModules().forEach(el -> {
                    el.setContainer((Pane) container.getContent());
                    recursivePopulate(el);
                });
            }
        }
    }

    @SuppressWarnings("unused")
    public Module getCurrentModule() {
        return currentModule.get();
    }

    public ObjectProperty<Module> currentModuleProperty() {
        return currentModule;
    }

    public final ObjectProperty<Callback<Module, Node>> cellFactoryProperty() {
        if (cellFactory == null) {
            cellFactory = new SimpleObjectProperty<>(this, "cellFactory");
        }
        return cellFactory;
    }

    public final void setCellFactory(Callback<Module, Node> value) {
        cellFactoryProperty().set(value);
    }

    @SuppressWarnings("unused")
    public final void setItems(ObservableList<Module> value) {
        itemsProperty().set(value);
    }

    public final ObservableList<Module> getItems() {
        return items == null ? null : items.get();
    }

    public final ObjectProperty<ObservableList<Module>> itemsProperty() {
        if (items == null) {
            items = new SimpleObjectProperty<>(this, "items");
        }
        return items;
    }
}
