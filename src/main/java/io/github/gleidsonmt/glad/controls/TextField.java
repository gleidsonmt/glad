package io.github.gleidsonmt.glad.controls;

import javafx.collections.SetChangeListener;
import javafx.css.PseudoClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/04/2025
 */
public class TextField extends javafx.scene.control.TextField implements Component {

    private List<String> pseudos;
    public TextField(String text) {
        super(text);

        getPseudoClassStates().addListener(new SetChangeListener<PseudoClass>() {
            @Override
            public void onChanged(Change<? extends PseudoClass> change) {
                if (pseudos == null) return;
                for (String state : pseudos) {
                    String opt =  state.substring( state.indexOf(":") +1);

                    List<String> list = new ArrayList<>(List.of(state.split(":")));
                    list.remove(list.size() -1);
//                    System.out.println("change.getSet() = " + change.getSet());

                    for (String item : list) {
                        if (change.getSet().contains(PseudoClass.getPseudoClass(item))) {
                            getStyleClass().addAll(opt);
                        } else {
                            System.out.println("opt = " + opt);
                            getStyleClass().removeAll(opt);
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
            }
        });

    }

    private void recur(String string) {
        if (string.contains(":")) {

        } else {

        }
    }

    @Override
    public void addClasses(String classes) {
        for (String clazz : classes.split(" ")) {
            if (clazz.contains(":")) {
                 if (pseudos == null) pseudos = new ArrayList<>();
                 pseudos.add(clazz);
            } else {
                getStyleClass().add(clazz);
            }
        }
    }
}
