package io.github.gleidsonmt.glad.drawer;

import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/04/2025
 */
public class SimpleDrawer extends VBox {

    private ListView<DrawerItem> drawerItems = new ListView<>();

    public SimpleDrawer(Module... modules) {

        GridPane drawerHeader = new GridPane();
        Label logo = new Label("Drawer, CO");
        logo.getStyleClass().addAll("h3", "font-instagram", "bold");
        SVGIcon icon = new SVGIcon(Icon.HUB);
        Label iconContainer = new Label();
        iconContainer.getStyleClass().addAll("rounded", "border-2", "border-light-gray", "padding-5");

        iconContainer.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        iconContainer.setGraphic(icon);
        logo.setGraphic(iconContainer);
        drawerHeader.add(logo, 0, 0);

        VBox drawerContent = new VBox();

        ScrollPane scroll = new ScrollPane(drawerContent);
        scroll.setFitToWidth(true);

        for (Module module : modules) {
            if (module instanceof View view) {
                drawerItems.getItems().add(
                        new SimpleDrawerItem(
                                module.getName(), module.getGraphic(), view.getContent()
                        )
                );
            }
        }

//        drawerItems.setItems(FXCollections.observableArrayList(
//                new NotificationDrawerItem("Orders", new SVGIcon(Icon.ORDERS), createCustomLabel(),
//                        createView(new StackPane(new Label("Orders View")))),
//                new SimpleDrawerItem("Apps", new SVGIcon(Icon.APPS),
//                        createView(new StackPane(new Label("Apps View")))),
//                new SimpleDrawerItem("Products", new SVGIcon(Icon.LOCAL_MALL),
//                        createView(new StackPane(new Label("Products View"))))
//        ));

        drawerItems.getSelectionModel().select(0);

        drawerItems.setCellFactory(new Callback<>() {
            @Override
            public ListCell<DrawerItem> call(ListView<DrawerItem> drawerItemListView) {
                return new ListCell<>() {
                    {
                        selectedProperty().addListener((_, _, newVal) -> {
                            DrawerCell cell = (DrawerCell) getGraphic();

                            if (newVal) {
                                cell.getPane().setStyle("-fx-background: -fx-accent; -text-color: white;");
                            } else {
                                cell.getPane().setStyle("-fx-background: transparent;");
                            }
                        });
                    }

                    @Override
                    protected void updateItem(DrawerItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            DrawerCell cell = new DrawerCell(item);
                            setGraphic(cell);
                            setText(null);
                            setMouseTransparent(false);
                        } else {
                            setItem(null);
                            setText(null);
                        }
                    }
                };
            }
        });

        drawerContent.getChildren().add(drawerItems);

        getChildren().setAll(drawerHeader, scroll);
        getStyleClass().addAll("bg-background", "border-r-2", "border-light-gray-2", "padding-10", "min-w-300");
    }

    public ReadOnlyObjectProperty<DrawerItem> selectedProperty() {
        return drawerItems.getSelectionModel().selectedItemProperty();
    }

    private Node createCustomLabel() {
        Text text = new Text("89");
        StackPane label = new StackPane(text);
        label.getStyleClass().addAll("min-size-20", "size-20", "bg-accent", "text-white", "bold", "align-center", "round");

        return label;
    }

    public ObservableList<DrawerItem> getDrawerItems() {
        return drawerItems.getItems();
    }
}
