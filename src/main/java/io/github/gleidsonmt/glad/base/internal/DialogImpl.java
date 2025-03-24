package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.dialog.Dialog;
import io.github.gleidsonmt.glad.dialog.DialogContainer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/03/2025
 */
public class DialogImpl implements Dialog {

    private Root root;

    public DialogImpl(Root root) {
        this.root = root;
    }

    @Override
    public void open(Node node) {
        root.flow().openAbsolute(new DialogContainer(node), Pos.CENTER, Insets.EMPTY);
    }

    @Override
    public void close() {
        root.flow().clear();
    }
}
