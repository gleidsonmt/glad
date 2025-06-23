package io.github.gleidsonmt.glad.base;

import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.base.responsive.*;
import io.github.gleidsonmt.glad.base.responsive.sizer.Sizer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/03/2025
 */
public class LayoutImpl extends BorderPane implements LayoutActions, Layout, Actionable<Break> {

//    private final Sizer<Break> sizer;

    private ObjectProperty<Module> view = new SimpleObjectProperty<>();

    public LayoutImpl() {
        this(null);
    }

    public LayoutImpl(Node node) {
        updateView(node);


        this.viewProperty().addListener(new ChangeListener<Module>() {
            @Override
            public void changed(ObservableValue<? extends Module> observable, Module oldValue, Module newValue) {
                if (newValue instanceof View view && view.getContent() != null) {
                    updateView(view.getContent());
                } else  {
                    updateView(new ErrorPage(newValue.getName()));
                }
            }
        });
    }

//    public Break getSize() {
//        return sizer.getSize(getWidth());
//    }

    public boolean biggerThan(Break first, Break second) {
        return first.getMax() > second.getMax();
    }

    public boolean lowerThan(Break first, Break second) {
        return first.getMax() < second.getMax();
    }

    @Override
    public void updateView(Node view) {
        setCenter(view);
    }

    @Override
    public void doAction(Break breaks) {
//        BreakPoint br = points.stream().filter(p -> p.getBreaks().contains(breaks)).findAny().orElse(null);
//        System.out.println("br = " + br);
//        if (br == null) {
//            return;
//        }
//        // Make an action defined by user.
//        // This action is convenient to be a new layout based on the scene size
//        br.getEventHandler().handle(new ActionEvent());
    }

    @Override
    public ObjectProperty<Module> viewProperty() {
        return view;
    }

    @Override
    public void setView(View view) {
        updateView(view.getContent());
    }

    @Override
    public void setBar(Node node) {
        this.setTop(node);
    }

    @Override
    public void setDrawer(Node node) {
        this.getChildren().remove(this.getLeft());
        this.setLeft(node);
    }

    @Override
    public Root getRoot() {
        return (Root) getScene().getRoot();
    }

    @Override
    public Flow flow() {
        throw new  RuntimeException("Error this implementation is invalid");
    }


}
