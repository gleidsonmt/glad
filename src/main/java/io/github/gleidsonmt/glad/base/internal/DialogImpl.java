package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.WrapperEffect;
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
    private boolean effect = false;

    public DialogImpl(Root root) {
        this.root = root;
    }

    public void open(Node node) {

        var alert = root.flow()
                .pos(Pos.CENTER)
                .width(width == -1 ? 600 : width)
                .height(height == -1 ? 400 : height)
                .anchor(anchor)
                .content(new DialogContainer(node))
                ;

        if (wrapperEffect != null) {
            root.wrapper()
                    .with(alert)
                    .show();
        } else  {
            alert.show();
        }



    }


    @Override
    public void show() {
        open(super.content);
    }

    @Override
    public void hide() {
        root.flow().remove(super.content.getParent());
        root.wrapper().hide();
    }


    @Override
    public Dialog effect() {
        this.effect = true;
        return this;
    }
}
