

package io.github.gleidsonmt.glad.base.responsive;

import io.github.gleidsonmt.glad.base.responsive.sizer.Size;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  03/02/2026
 */
public class Container extends AbstractContainer<Size> {

    public Container() {
        this(null, DefaultBreak.values());
    }

    public Container(Node node) {
        this(node, DefaultBreak.values());
    }

    public Container(Size[] values) {
        this(null, values);
    }

    public Container(Node node, Size[] values) {
        super(values);
        if (node != null) this.getChildren().add(node);

//        this.getChildren().addListener(new ListChangeListener<Node>() {
//            @Override
//            public void onChanged(Change<? extends Node> change) {
//                if (change.next()) {
//                    change.getList().forEach(el -> {
//                    var w = sizer.getSize(getWidth());
//                        System.out.println("el = " + w);
//                        System.out.println("el = " + el.getStyleClass());
//                        el.setStyle(el.getStyle() + el);
//                        getBreakpoints().add(new BreakPoint((_) -> {
//                            if (el.getStyleClass().stream().anyMatch(e -> e.contains(":"))) {
//
//                            }
//                            System.out.println("jababa");
//                            System.out.println(w.toString());
//                        }, w));
////                        System.out.println("breaker.getPoints() = " + breaker.getPoints());
////                        breaker.getPoints().add(new BreakPoint(_ -> {
////
////                            System.out.println(" you're a bitch ");
////                        }, DefaultBreak.MOBILE));
//
////                        el.getStyleClass().stream().map(String::toLowerCase).filter(s -> s.contains("size")).findFirst().ifPresent(s -> {})
//                    });
//                }
//            }
//        });
    }

//    public Container() {
//        this.getChildren().addListener(new ListChangeListener<Node>() {
//            @Override
//            public void onChanged(Change<? extends Node> change) {
//                if (change.next()) {
//                    change.getList().forEach(el -> {
//                        var w = sizer.getSize(getWidth());
//                        System.out.println("el = " + w);
//                        System.out.println("el = " + el.getStyleClass());
//                        el.setStyle(el.getStyle() + el);
////                        System.out.println("breaker.getPoints() = " + breaker.getPoints());
////                        breaker.getPoints().add(new BreakPoint(_ -> {
////
////                            System.out.println(" you're a bitch ");
////                        }, DefaultBreak.MOBILE));
//
////                        el.getStyleClass().stream().map(String::toLowerCase).filter(s -> s.contains("size")).findFirst().ifPresent(s -> {})
//                    });
//                }
//            }
//        });
//    }


}
