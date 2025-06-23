package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.responsive.Breaker;
import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/06/2025
 */
public interface Root  {

    ReadOnlyDoubleProperty widthProperty();

    ReadOnlyDoubleProperty heightProperty();

    Wrapper wrapper();

    Behavior behavior();
}
