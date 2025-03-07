package io.github.gleidsonmt.glad.demos.presentations;

import io.github.gleidsonmt.glad.base.Container;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.presentation.CssPresentation;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/02/2025
 */
public class TextPres extends Container {

    public TextPres() {

        ScrollPane scrollPane = new ScrollPane(
                new Presentation()
                        .h2("Text", null)
                        .text("The Text class defines a node that displays a text. Paragraphs are separated by \n and the text is wrapped on paragraph boundaries.")
                        .legend("(The base font here is set to Poppins, by default is not apply, but it's better to add all fonts before the app load. At the end you can see the code to add.)")
                        // .link([link to explanation]) ir para temas
                        .demonstration(List.of(
                                new Text("Lorem ipsum dolor color")
                        ), "Text text = new Text(\"Lorem ipsum dolor color\");", "")
                        .h3("Typographic")
                        .code("ThemeProvider.install(root, \n\tCss.COLORS, \n\tCss.TYPOGRAPHIC);", "java")
                        .cssTable(
                                new CssPresentation("h1", "-fx-font-size: 12"),
                                new CssPresentation("h2", "-fx-font-size: 12"),
                                new CssPresentation("h3", "-fx-font-size: 12"),
                                new CssPresentation("h4", "-fx-font-size: 12"),
                                new CssPresentation("h5", "-fx-font-size: 12"),
                                new CssPresentation("h6", "-fx-font-size: 12")
                        )
                        .demonstration(List.of(
                                createDemo("h1", "h1"),
                                createDemo("h2", "h2"),
                                createDemo("h3", "h3"),
                                createDemo("h4", "h4"),
                                createDemo("h5", "h5"),
                                createDemo("h6", "h6")
                        ), """
                                    text.getStyleClass().add("h-[*size-number*]");
                                """, "")
                        .h3("Fonts")
                        .code("ThemeProvider.install(root, Css.POPPINS);")

                        .build()
                        .getRoot()
        );
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20));
        updateView(scrollPane);
    }

    private Text createDemo(String _text, String... _classes) {
        Text text = new Text(_text);
        text.getStyleClass().addAll(_classes);
        return text;
    }
}
