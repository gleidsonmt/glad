package io.github.gleidsonmt.glad.controls.skin;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.Editor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/05/2024
 */
public class MaterialSkinTextField extends EditorSkin {

    public MaterialSkinTextField(Editor control) {
        super(control);
//        control.setContextMenu(new ContextMenu(new MenuItem("Material")));

//        String baseName = "com/sun/javafx/scene/control/skin/resources/controls";
//        ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.getDefault());

        ResourceBundle bundle = ResourceBundle.getBundle("io.github.gleidsonmt.glad.i18n.controls", Locale.getDefault());
//        ResourceBundle rc = ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());
        MenuItem menuUndo = new MenuItem(bundle.getString("TextInputControl.menu.Undo"));
//        MenuItem menuUndo = new MenuItem(rc.getString(""));

        menuUndo.setGraphic(new SVGIcon(Icon.UNDO));
        menuUndo.setOnAction(_ -> control.undo());
        menuUndo.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        menuUndo.disableProperty().bind(control.undoableProperty().not());
        menuUndo.getStyleClass().add("menu-item-first");

        MenuItem menuRedo = new MenuItem(bundle.getString("TextInputControl.menu.Redo"));
        menuRedo.setGraphic(new SVGIcon(Icon.REDO));
        menuRedo.setOnAction(_ -> control.redo());
        menuRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        menuRedo.disableProperty().bind(control.redoableProperty().not());

        MenuItem menuCut = new MenuItem(bundle.getString("TextInputControl.menu.Cut"));
        menuCut.setGraphic(new SVGIcon(Icon.CUT));
        menuCut.setOnAction(e -> control.cut());
        menuCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        menuCut.disableProperty().bind(control.selectedTextProperty().isEmpty());

        MenuItem menuCopy = new MenuItem(bundle.getString("TextInputControl.menu.Copy"));
        menuCopy.setGraphic(new SVGIcon(Icon.COPY));
        menuCopy.setOnAction(e -> {
            control.selectAll();
            control.copy();
            control.deselect();
        });
        menuCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        menuCopy.disableProperty().bind(control.textProperty().isEmpty());

        MenuItem menuRemove = new MenuItem(bundle.getString("TextInputControl.menu.DeleteSelection"));
        menuRemove.setGraphic(new SVGIcon(Icon.DELETE));
        menuRemove.setOnAction(e -> control.clear());
        menuRemove.setAccelerator(new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN));
        menuRemove.disableProperty().bind(control.selectedTextProperty().isEmpty());

        MenuItem menuPaste = new MenuItem(bundle.getString("TextInputControl.menu.Paste"));
        menuPaste.setGraphic(new SVGIcon(Icon.PASTE));
        menuPaste.setOnAction(e -> control.paste());
        menuPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));

        MenuItem menuAll = new MenuItem(bundle.getString("TextInputControl.menu.SelectAll"));
        menuAll.setGraphic(new SVGIcon(Icon.SELECT_ALL));
        menuAll.setOnAction(e -> control.selectAll());
        menuAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

        menuAll.getStyleClass().add("menu-item-last");

        control.setContextMenu(
                new CustomContextMenu(
                        menuUndo, menuRedo, menuCut, menuCopy, menuPaste, menuRemove,
                        new SeparatorMenuItem(), menuAll
                ));

    }

}

class CustomContextMenu extends ContextMenu {

    public CustomContextMenu(MenuItem... menuItem) {
        this.getItems().addAll(menuItem);
        this.getScene().getRoot().setStyle("-fx-background-color: transparent;");
        this.setPrefWidth(200);

//        this.setStyle("-fx-background-color: transparent; -fx-border-color: gray; -fx-background-radius: 10px;  -fx-border-radius: 10px; ");
    }

}

