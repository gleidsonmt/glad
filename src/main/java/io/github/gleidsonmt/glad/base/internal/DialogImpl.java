

package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.dialog.Dialog;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on  22/03/2025
 */
public class DialogImpl extends DialogAbstract<Dialog> implements Dialog {

    public DialogImpl(Root root) {
        super(root);
        with = WrapperEffect.GRAY;
    }

    public void open(Node node) {

//        if (this.with != null && !root.getChildren().contains(foreground)) {
//            root.setForeground(foreground.restyle(with, root));
//        } else {
//            foreground.restyle(with, root);
//        }

        var dialog = new DialogContainer(node);
        dialog.setViewOrder(2);

        if (root.isBlocked()) {
//            root.block();
            root.getForeground().restyle(with, dialog.getViewOrder() + 1);
        } else {
//            root.unblock();
            root.getForeground().restyle(null, 7);
        }


        root.flow()
                .pos(pos)
//                .width(width == -1 ? 600 : width)
//                .height(height == -1 ? 400 : height)
                .width(width.get())
                .height(height.get())

                .anchor(anchor)
                .content(dialog)
                .show();

            super.show();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void show() {
        open(super.content);
    }

    @Override
    public void hide() {
        if (this.content == null) return;
        super.hide();
        root.flow().remove(super.content.getParent());
    }


    @Override
    public Dialog effect() {
        this.with = WrapperEffect.GRAY;
        return this;
    }

}
