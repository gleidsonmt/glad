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
public interface Layout  {

    @Deprecated
    default void setBar(Node node){};

    @Deprecated
    default void setAside(Node node) {}

    @Deprecated
    default void setFooter(Node node) {}

    @Deprecated
    default void setDrawer(Node node){};

    default Node getDrawer() { return null; }

//    ObjectProperty<Module> viewProperty();

//    void setPadding(Insets insets);

//    void setView(View view);

//    void updateView(View view);

//    Root getRoot();

//    Flow flow();

}
