package io.github.gleidsonmt.glad.base.drawer;

import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.ModuleView;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.View;
import io.github.gleidsonmt.glad.drawer.DrawerItem;
import io.github.gleidsonmt.glad.drawer.DrawerMenu;
import io.github.gleidsonmt.glad.drawer.DrawerSeparator;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
 */
@ApiStatus.Experimental
public class Drawer extends VBox {

    private final ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();
    private ObjectProperty<Callback<Module, Node>> cellFactory;
    private ObjectProperty<ObservableList<Module>> items;

    private DrawerContainer drawerContainer = null;
    private final ToggleGroup group = new ToggleGroup();

    private final VBox searchBox = new VBox();
    private final VBox defaultBox = new VBox();

    public Drawer() {
        this(FXCollections.observableArrayList());
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

    public Drawer(@NotNull ObservableList<Module> modules,  @NotNull Callback<Module, Node> cellFactory) {
        this.setId("drawer");
        this.drawerContainer = new DrawerContainer(defaultBox);
        defaultBox.setId("drawer-content");

        cellFactoryProperty().addListener((_, _, newCellFactory) -> {
            if (newCellFactory != null) {
//                defaultBox.getChildren().clear();
//                Objects.requireNonNull(itemsProperty().get()).forEach(this::recursivePopulate);
            }
        });

        itemsProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                defaultBox.getChildren().clear();
                Objects.requireNonNull(newValue).forEach(this::recursivePopulate);

//                newValue.addListener(new ListChangeListener<Module>() {
//                    @Override
//                    public void onChanged(Change<? extends Module> c) {
//                        if (c.next()) {
//                            if (c.wasAdded()) {
//                                c.getAddedSubList().forEach(Drawer.this::recursivePopulate);
//                            } else if (c.wasRemoved()) {
//                                System.out.println("c.getRemoved() = " + c.getRemoved());
//                                c.getRemoved().forEach(module -> {
//                                    Module exclude = find((List<Module>) c.getRemoved(), module.getName());
////                                    find(c.get().get), module.getName().toLowerCase());
//                                    defaultBox.getChildren().remove(module);
//                                    if (module.getContainer() != null) {
//                                        module.getContainer().getChildren().clear();
//                                    }
//                                });
//                            }
//                        }
//                    }
//                });
            }
        });



        cellFactoryProperty().set(cellFactory);
        itemsProperty().set(modules);

        this.getChildren().addAll(drawerContainer);
//
        setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(drawerContainer, Priority.ALWAYS);
        this.setPrefWidth(250);
//
//        currentModule.addListener((_, _, newValue) -> group.getToggles().forEach(e -> {
//            if (e.getUserData() == newValue) {
//                group.selectToggle(e);
//            }
//        }));
//
//        group.selectedToggleProperty().addListener((_, oldValue, newValue) -> {
//            if (newValue != null) {
//                r((ToggleButton) newValue, true);
//            }
//            if (oldValue != null) {
//                Node oldParent = getRoot(((ToggleButton) oldValue));
//                Node newParent = getRoot(((ToggleButton) newValue));
//                if (oldParent != newParent) {
//                    r((ToggleButton) oldValue, false);
//                    Platform.runLater(() -> {
//                        if (newParent instanceof DrawerMenu parent) {
//                            parent.setExpanded(true);
//                        }
//                        if (oldParent instanceof DrawerMenu p) {
//                            p.setExpanded(false);
//                        }
//                    });
//                }
//            }
//        });
//
//        if (!group.getToggles().isEmpty()) {
//            group.selectToggle(group.getToggles().getFirst());
//            currentModule.setValue((ModuleView) group.getToggles().get(1).getUserData());
//        }
    }

    public void setSearchable(StringProperty property, Predicate<String> predicate) {
        property.addListener((_, _, newVal) -> {
            if (!newVal.isEmpty()) {
                drawerContainer.setContainer(searchBox);
                searchBox.getChildren().clear();
//
                find(predicate)
                        .forEach(e -> {
                            Optional<BoxModule> opt = findModuleInSearchBox(e); // verify if box has e
                            if (opt.isEmpty() && e.getParent() != null) { // if box doesn't have a module and module has a parent
                                // add a new BoxModule with module
                                searchBox.getChildren().add(new BoxModule(e.getParent().getName(), createToggle(e)));
                            } else if (e.getParent() != null && opt.isPresent()) { // if module already in search box and module has a parent
                                // find, and add to a BoxModule already in search box.
                                getBoxModule(e).get().getChildren().add(createToggle(e));
                            }
                        });
            } else {
                drawerContainer.setContainer(defaultBox);
                VBox.setVgrow(drawerContainer, Priority.ALWAYS);
            }
        });
    }

    /**
     * If drawer is in mode of search, find a moduleImpl.
     *
     * @param module The moduleImpl in the SearchBox.
     * @return The BoxModule (VBox) that is equal to the moduleImpl name.
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

    public ToggleButton createToggle(Module moduleImpl) {
        ToggleButton b = new ToggleButton(moduleImpl.getName());
//        b.setCache(true);
//        b.setCacheHint(CacheHint.QUALITY);
        b.setUserData(moduleImpl);
        b.getStyleClass().add("drawer-item");
        b.setAlignment(Pos.CENTER_LEFT);
        b.setPrefWidth(Double.MAX_VALUE);
        b.setOnMouseClicked(e -> currentModule.set(moduleImpl));

        b.addEventFilter(MouseEvent.MOUSE_RELEASED, _ -> {
            Root root = (Root) getScene().getRoot();
            root.behavior().closeDrawer();
        });

        if (moduleImpl.getGraphic() != null) {
            b.setGraphic(moduleImpl.getGraphic());
        }

        group.getToggles().add(b);
        return b;
    }



    public void makeFirstLevel(Module module) {

//        var callback = cellFactory.get().call(module);
//        if (callback instanceof DrawerMenu menu) {
//            if (module.getModules() == null && module.getModules().isEmpty()) return;
//            menu.getStyleClass().add("module-first");
//            ((VBox) this.drawerContainer.getContent()).getChildren().add(menu);
//
//            module.getModules().forEach(el -> { // o q esta dentro do menu
////                System.out.println("el = " + el);
////                System.out.println("(el instanceof View) = " + (el instanceof View));
////                System.out.println("(el instanceof View) = " + (el instanceof ModuleView));
//                if (!el.getModules().isEmpty()) {
//                    el.getModules().forEach(e -> {
//                        e.setContainer((Pane) menu.getContent());
//                        System.out.println("e = " + e);
//                        recurse(e);
//                    });
//
////                    if (el instanceof View) {
////                        ToggleButton button = createToggle(el);
////                        ((Pane) menu.getContent()).getChildren().add(button);
////                        button.setOnMouseClicked(e -> currentModule.set(el));
////                    } else {
//////                        System.out.println("fugg");
////                        ((Pane) menu.getContent()).getChildren().add(cellFactory.get().call(el));
////                        el.getModules().forEach(e -> {
////                            e.setContainer((Pane) menu.getContent());
////                            recurse(e);
////                        });
////                    }
////
//
//                } else {
//                    ((Pane) menu.getContent()).getChildren().add(cellFactory.get().call(el));
//                }
//
//            });
//
////                ((VBox) this.drawerContainer.getContent()).getChildren().add(callback);
//        } else {
//            ((VBox) this.drawerContainer.getContent()).getChildren().add(callback);
//        }

//        if (module instanceof ModuleView moduleView) {
//            if (module.getModules() == null) return;
////            DrawerMenu container = (DrawerMenu) cellFactory.get().call(module);
//            System.out.println("cellFactory = " + cellFactory.get().call(module));
////            container.getStyleClass().add("module-first");
////
////            if (module.getModules() != null || !module.getModules().isEmpty()) {
////                module.getModules().forEach(el -> {
////                    if (el != null && !el.getModules().isEmpty()) {
////                        TitledPane pane = (TitledPane) cellFactory.get().call(module);
////                        ((Pane) container.getContent()).getChildren().add(pane);
////                        el.setContainer((Pane) container.getContent());
////                        el.getModules().forEach(e -> {
////                            e.setContainer((Pane) pane.getContent());
////                            recurse(e);
////                        });
////                    }
////                });
//            if (module.getModules() != null || !module.getModules().isEmpty()) {
//////            VBox.setMargin(b, new Insets(0, 0, 0, 10));
////                module.getModules().forEach(el -> {
//////                    if (el instanceof View view) {
////                    if (el != null && !el.getModules().isEmpty()) {
////                        TitledPane pane = createPanel(el);
////                        ((Pane) container.getContent()).getChildren().add(pane);
////                        el.setContainer((Pane) container.getContent());
////                        el.getModules().forEach(e -> {
////                            e.setContainer((Pane) pane.getContent());
////                            recurse(e);
////                        });
////                    } else {
////                        ToggleButton button = createToggle(el);
////
////                        ((Pane) container.getContent()).getChildren().add(button);
////                        button.setOnMouseClicked(e -> currentModule.set(el));
////                    }
////                });
//            }
//        }

    }

    public void makeFirstLevelOld(Module module) {

        if (module instanceof ModuleSeparator moduleSeparator) {

            Label label = new Label(moduleSeparator.getName());
            label.setGraphic(moduleSeparator.getGraphic());
            label.getStyleClass().add("font-instagram-headline");
//            label.setStyle("-fx-font-family: \"Instagram Sans\"; -fx-font-size: 12px;");
//            label.setCache(true);
//            label.setCacheHint(CacheHint.QUALITY);
            VBox box = new VBox(label, new Separator());
//            box.setPadding(new Insets(0, 5,0,5));
            VBox.setMargin(box, new Insets(10, 5, 0, 5));
            box.setSpacing(10);
            defaultBox.getChildren().addAll(box);
        }

        if (module.getModules() == null) return;

        if (module.getModules().isEmpty()) {
//            this.getChildren().add(createToggle(moduleImpl));
            ((VBox) this.drawerContainer.getContent()).getChildren().add(createToggle(module));
        } else {
            TitledPane container = createPanel(module, true);
            container.getStyleClass().add("module-first");
//            this.getChildren().add(container);
            ((VBox) this.drawerContainer.getContent()).getChildren().add(container);
            if (module.getModules() != null || !module.getModules().isEmpty()) {
//            VBox.setMargin(b, new Insets(0, 0, 0, 10));
                module.getModules().forEach(el -> {
//                    if (el instanceof View view) {
                    if (el != null && !el.getModules().isEmpty()) {
                        TitledPane pane = createPanel(el);
                        ((Pane) container.getContent()).getChildren().add(pane);
                        el.setContainer((Pane) container.getContent());
                        el.getModules().forEach(e -> {
                            e.setContainer((Pane) pane.getContent());
                            recursivePopulate(e);
                        });
                    } else {
                        ToggleButton button = createToggle(el);

                        ((Pane) container.getContent()).getChildren().add(button);
                        button.setOnMouseClicked(e -> currentModule.set(el));
                    }
                });
            }
        }
    }

    private Node call(Module module) {
        return cellFactoryProperty().get().call(module);
    }

    public void recursivePopulate(Module moduleImpl) {
        if (moduleImpl instanceof View view) {

            ToggleButton b = (ToggleButton) call(view);
            group.getToggles().add(b);
            b.setOnMouseClicked(e -> currentModule.set(moduleImpl));

            if (view.getContainer() != null) {
                view.getContainer().getChildren().add(b);
            } else {
                ((VBox) this.drawerContainer.getContent()).getChildren().add(b);
            }
        } else if (moduleImpl instanceof ModuleSeparator moduleSeparator) {
            ((VBox) this.drawerContainer.getContent()).getChildren().add(call(moduleSeparator));

        } else if (moduleImpl instanceof ModuleView moduleView) {
            TitledPane container = (TitledPane) call(moduleImpl);

            if (moduleView.getContainer() == null) {
                container.getStyleClass().add("module-first");
                ((VBox) this.drawerContainer.getContent()).getChildren().add(container);
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

    private @NotNull TitledPane createPanel(Module module) {
        return createPanel(module, false);
    }

    private @NotNull TitledPane createPanel(@NotNull Module module, boolean first) {
        VBox content = new VBox();
        content.getStyleClass().add("container");
        TitledPane titledPane = new TitledPane(module.getName(), content);

        if (module.getGraphic() != null) {
            titledPane.setGraphic(module.getGraphic());
        }
        titledPane.setExpanded(false);

        titledPane.setContentDisplay(ContentDisplay.RIGHT);
        titledPane.setAlignment(Pos.TOP_RIGHT);
        titledPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        titledPane.getStyleClass().add("drawer-menu");
        content.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        if (!module.isAnimated() && !first) {
            ToggleButton b = new ToggleButton(module.getName());
            b.setUserData(module);
            b.getStyleClass().add("drawer-item");
            b.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            b.setAlignment(Pos.CENTER_LEFT);
            b.setPrefWidth(230);
            group.getToggles().add(b);
            titledPane.getStyleClass().add("module-item");

            titledPane.setGraphic(b);
            titledPane.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

            titledPane.setCollapsible(false);
            titledPane.setAnimated(false);

            b.setOnMouseClicked(e ->
            {
                if (!(e.getTarget() instanceof VBox)) {
                    currentModule.set(module);
                }
            });
        }

        return titledPane;
    }

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
