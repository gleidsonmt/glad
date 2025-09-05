package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.dialog.Dialog;
import io.github.gleidsonmt.glad.dialog.DialogContainer;
import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/03/2025
 */
public class DialogImpl extends DialogAbstract<Dialog> implements Dialog {

    private final Root root;
    protected Node content;

    public DialogImpl(Root root) {
        this.root = root;
    }

    public void open(Node node) {
//        root.flow().openAbsolute(new DialogContainer(node), Pos.CENTER, Insets.EMPTY);
        root.flow()
                .pos(Pos.CENTER)
//                .anchor(Anchor.BOTTOM)
//                .with(WrapperEffect.GRAY)
                .content(new DialogContainer(node))
                .show();
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }



}
