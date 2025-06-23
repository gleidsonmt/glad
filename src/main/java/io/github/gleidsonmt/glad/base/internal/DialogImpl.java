package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.RootImpl;
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

    private RootImpl rootImpl;

    public DialogImpl(RootImpl rootImpl) {
        this.rootImpl = rootImpl;
    }

    @Override
    public void open(Node node) {
        rootImpl.flow().openAbsolute(new DialogContainer(node), Pos.CENTER, Insets.EMPTY);
    }

    @Override
    public void close() {
        rootImpl.flow().clear();
    }
}
