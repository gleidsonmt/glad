package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.base.responsive.BreakPoint;
import io.github.gleidsonmt.glad.base.responsive.Breaker;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public interface Layout extends Breaker {

    ObjectProperty<Module> viewProperty();

    void setPadding(Insets insets);

    void setView(View view);

    void setBar(Node node);

    void setDrawer(Node node);

    default void setAside(Node node) {}

    default void setFooter(Node node) {}

    Root getRoot();

    Flow flow();


}
