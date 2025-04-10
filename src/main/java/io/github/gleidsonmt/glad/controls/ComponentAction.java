package io.github.gleidsonmt.glad.controls;

import javafx.collections.SetChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
public interface ComponentAction {

    List<String> pseudos = new ArrayList<>();

    default void retrieveClasses(Node node, String classes) {

        node.getPseudoClassStates().addListener((SetChangeListener<PseudoClass>) change -> {
            for (String state : pseudos) {
                String opt =  state.substring( state.indexOf(":") +1);

                List<String> list = new ArrayList<>(List.of(state.split(":")));
                list.remove(list.size() -1);
//                    System.out.println("change.getSet() = " + change.getSet());

                for (String item : list) {
                    if (change.getSet().contains(PseudoClass.getPseudoClass(item))) {
                        node.getStyleClass().addAll(opt);
                    } else {
                        System.out.println("opt = " + opt);
                        node.getStyleClass().removeAll(opt);
                    }
                }

//                    System.out.println("list = " + list);
//                    System.out.println("cls = " + cls);
//                    System.out.println("opt = " + opt);

//                    System.out.println("state = " + state);
//
//                    if (change.getSet().contains(PseudoClass.getPseudoClass(cls))) {
//                        getStyleClass().add(opt);
//                    } else {
//                        getStyleClass().remove(opt);
//                    }
            }
        });
        Arrays.stream(classes.split(" ")).forEach(clazz -> {
            if (clazz.contains(":")) {
                pseudos.add(clazz);
            } else {
                node.getStyleClass().add(clazz);
            }
        });
    }
}
