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
import javafx.scene.layout.Region;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public interface Layout  {

    default Region getDrawer() { return null; }

    default Region getAside() { return null; }

    default Node getBar() { return null; }

    default Node getFooter() { return null; }

    default void updateView(Module oldVal, Module newVal) {}

}
