package io.github.gleidsonmt.glad.tail;

import io.github.gleidsonmt.glad.base.Root;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 06/10/2026
 */
public class ClassWatcher {

    private final TailwindWatcher tailwindWatcher = new TailwindWatcher();

    public ClassWatcher(Scene scene) {
        Platform.runLater(() -> {

            try {
                tailwindWatcher.start();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
//        // 1. Processa todos os componentes que já estão na tela atualmente
            watchContainer(scene.getRoot());

            // 2. Cria um listener reativo global na raiz da Scene.
            // Toda vez que um novo painel, botão ou nó entrar no layout, ele ativa o monitoramento automaticamente.
//            scene.rootProperty().addListener((_, _, newRoot) -> {
//                if (newRoot != null) {
//                    watchContainer(newRoot);
//                }
//            });

            if (scene.getRoot() instanceof Root root) {
                root.contentProperty().addListener((_, _, newValue) -> {
                    watchContainer(newValue);
                });
            }
        });
    }

    // Método interno que varre e escuta contêineres recursivamente
    private void watchContainer(Node node) {
        if (node instanceof Parent parent) {
            // Escuta se novos filhos entrarem neste contêiner específico (ex: um VBox ou HBox)
            parent.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
                @Override
                public void onChanged(Change<? extends Node> change) {

                    while (change.next()) {
                        if (change.wasReplaced()) {
                            for (Node removedNode : change.getRemoved()) {
                                if (removedNode instanceof Parent par) {
                                    par.getChildrenUnmodifiable().removeListener(this);
                                }
                            }
                            for (Node addedNode : change.getAddedSubList()) {
                                if (addedNode instanceof Parent par) {
                                    par.getChildrenUnmodifiable().removeListener(this);
                                }
                                watchContainer(addedNode);
                            }

                        } else if (change.wasRemoved()) {
                            System.out.println("removed = " + change.getRemoved());
                            for (Node removedNode : change.getRemoved()) {
                                if (removedNode instanceof Parent par) {
                                    par.getChildrenUnmodifiable().removeListener(this);
                                }
                            }
                        } else if (change.wasAdded()) {
                            for (Node addedNode : change.getAddedSubList()) {
                                if (addedNode instanceof Parent par) {
                                    par.getChildrenUnmodifiable().removeListener(this);
                                }
                                watchNode(addedNode);
                            }
                        }
                    }
                }
            });

            // Varre recursivamente os filhos que já existem lá dentro
            for (Node child : parent.getChildrenUnmodifiable()) {
                watchContainer(child);
            }
        }

        // Ativa o monitor de StyleClass no nó atual
        watchNode(node);
    }

    // Ativa o listener individual de StyleClass (A ponte reativa que já criamos antes)
    private void watchNode(Node node) {
        if (node instanceof Node component) {

            // Evita adicionar o mesmo listener duplicado se o componente já estiver sendo vigiado
//            if (component.getProperties().containsKey("tailwind-watched")) return;
//            component.getProperties().put("tailwind-watched", true);


            component.getStyleClass().addListener(new ListChangeListener<>() {
                @Override
                public void onChanged(Change<? extends String> change) {
                    while (change.next()) {
                        if (change.wasAdded()) {
                            for (String className : change.getAddedSubList()) {

                                Platform.runLater(() -> {

                                    component.getStyleClass().removeListener(this);
                                    component.getStyleClass().remove(className); // Remove a versão com []

                                    var cleaned = new ClassConverter().clean(className);
//
                                    // Evita duplicar se a classe limpa já existir no componente
                                    if (!component.getStyleClass().contains(cleaned)) {
                                        component.getStyleClass().add(cleaned);
                                    }

                                    tailwindWatcher.append(cleaned);
                                    // 3. Re adiciona o listener para monitorar futuras inserções legítimas
                                    component.getStyleClass().addListener(this);
                                });

                            }
                        }
                    }
                }
            });

            tailwindWatcher.write(component.getStyleClass());

            // Processa as classes que o componente já possui no momento em que foi descoberto
            List<String> classes = new ArrayList<>(component.getStyleClass());

            for (String classeLimpa : classes) {
                component.getStyleClass().remove(classeLimpa);
                component.getStyleClass().add(new ClassConverter().clean(classeLimpa));
            }

        }
    }
}