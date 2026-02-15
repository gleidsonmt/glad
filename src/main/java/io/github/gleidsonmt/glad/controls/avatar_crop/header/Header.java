package io.github.gleidsonmt.glad.controls.avatar_crop.header;

import io.github.gleidsonmt.glad.controls.avatar_crop.CloseAction;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/11/2024
 */
public class Header extends GridPane {

    private CloseAction onClose;

    public Header(Icon _icon, String _title) {
        this.setMaxWidth(500);
        Text title = new Text(_title);

        Button btnClose = new Button();
        btnClose.getStyleClass().addAll("border-button");
        btnClose.setMaxSize(40, 30);

        btnClose.setOnAction(_ -> {if (onClose != null)  onClose.close();});

        btnClose.setCancelButton(true);
        btnClose.setGraphic(new SVGIcon(Icon.CLEAR));

        SVGIcon icon = new SVGIcon(_icon);

        this.getChildren().setAll(icon, title, btnClose);

        GridPane.setColumnIndex(icon, 0);
        GridPane.setColumnIndex(title, 1);
        GridPane.setColumnIndex(btnClose, 2);
        this.setHgap(10);

        GridPane.setHgrow(title, Priority.ALWAYS);
        this.setMinHeight(50);


    }

    public void setOnClose(CloseAction close) {
        this.onClose = close;
    }


}
