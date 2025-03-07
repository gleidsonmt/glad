package io.github.gleidsonmt.glad.base.drawer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/07/2024
 */
public class Drawer extends VBox {

    private ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

    private List<Module> modules;

    private ToggleGroup group = new ToggleGroup();

    public Drawer(Module... _modules) {
        this(List.of(_modules));
    }

    public Drawer(List<Module> _modules) {
        this.modules = _modules;
        this.setId("drawer");
        _modules.forEach(this::makeFirstLevel);
        this.setPrefWidth(250);
        this.setMaxWidth(250);
//        getStylesheets().add(Objects.requireNonNull(Start.class.getResource("css/drawer/variante_one.css")).toExternalForm());

        currentModule.addListener((observableValue, oldValue, newValue) -> group.getToggles().forEach(e -> {
            if (e.getUserData() == newValue) {
                group.selectToggle(e);
            }
        }));

        group.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                r((ToggleButton) newValue, true);
            }
            if (oldValue != null) {
                Node oldParent = getRoot(((ToggleButton) oldValue));
                Node newParent = getRoot(((ToggleButton) newValue));
                if (oldParent != newParent) {
                    r((ToggleButton) oldValue, false);
                }
            }
        });

        if (!group.getToggles().isEmpty()) {
            group.selectToggle(group.getToggles().get(0));
            currentModule.setValue((Module) group.getToggles().get(0).getUserData());
        }
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
        this.getChildren().add(0, node);
    }

    private Node getRoot(Node module) {

        if (module != null && module.getStyleClass().contains("module-first")) {
            return module;
        } else {
            if (module != null) return getRoot(module.getParent());
        }
        return null;
    }

    public void r(Node module, boolean active) {
        if (module instanceof TitledPane titledPane && titledPane.getStyleClass().contains("module-first")) {
            update(titledPane, active);
        } else {
            if (module.getParent() == null)return;
            r(module.getParent(), active);
        }
    }

    private ToggleButton createToggle(Module module) {
        ToggleButton b = new ToggleButton(module.getName());
        b.setUserData(module);
        b.getStyleClass().add("drawer-item");
        b.setAlignment(Pos.CENTER_LEFT);
        b.setPrefWidth(Double.MAX_VALUE);
        b.setOnMouseClicked(e -> currentModule.set(module));
        if (module.getGraphic() != null) {
            b.setGraphic(module.getGraphic());
        }
        group.getToggles().add(b);
        return b;
    }

    public void makeFirstLevel(Module module) {

        if (module.getModules().isEmpty()) {
            this.getChildren().add(createToggle(module));
        } else {
            TitledPane container  = createPanel(module, true);
            container.getStyleClass().add("module-first");
            this.getChildren().add(container);
            if (!module.getModules().isEmpty()) {
////            VBox.setMargin(b, new Insets(0, 0, 0, 10));
                module.getModules().forEach(el -> {
//                    if (el instanceof View view) {
                        if (!el.getModules().isEmpty()) {
                            TitledPane pane = createPanel(el);
                            ((Pane) container.getContent()) .getChildren().add(pane);
                            el.setContainer((Pane) container.getContent());
//                            System.out.println(el.getModules());
                            el.getModules().forEach(e -> {
                                e.setContainer((Pane) pane.getContent());
                                recurse(e);
                            });
                        } else {
                            ToggleButton button = createToggle(el);
                            ((Pane) container.getContent()) .getChildren().add(button);
                            button.setOnMouseClicked(e -> currentModule.set(el));
                        }
                });
            }
        }
    }

    public void recurse(Module module) {
        if (module instanceof View) {
            ToggleButton b = createToggle(module);

            if (module.getContainer() != null) {
                module.getContainer().getChildren().add(b);
            } else {
                this.getChildren().add(b);
            }
        } else {

            TitledPane container = createPanel( module);
            module.getContainer().getChildren().add(container);
            if (!module.getModules().isEmpty()) {
                module.getModules().forEach(el -> {
                    el.setContainer((Pane) container.getContent());
                    recurse(el);
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
                if (!(e.getTarget() instanceof VBox))  {
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

}
