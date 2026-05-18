

package io.github.gleidsonmt.glad.controls.loaders;

import javafx.beans.property.StringProperty;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a>
 * Create on  13/09/2024
 */
@SuppressWarnings("unused")
public interface SuspenseLoader {

    StringProperty titleProperty();

    StringProperty legendProperty();

    void setTitle(String _title);

    void setLegend(String _legend);
}
