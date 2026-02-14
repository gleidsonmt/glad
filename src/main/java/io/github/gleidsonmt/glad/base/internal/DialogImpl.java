package io.github.gleidsonmt.glad.base.internal;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.dialog.Dialog;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/03/2025
 */
public class DialogImpl extends DialogAbstract<Dialog> implements Dialog {

    public DialogImpl(Root root) {
        super(root);
    }

    public void open(Node node) {

        if(this.wrapperEffect != null) {
            root.getChildren().add(foreground.restyle(wrapperEffect, root));
        }

        root.flow()
                .pos(pos)
                .width(width == -1 ? 600 : width)
                .height(height == -1 ? 400 : height)
                .anchor(anchor)
                .content(new DialogContainer(node))
                .show();


        reset();
    }

    private void reset() {
        wrapperEffect = null;
    }

    @Override
    public void show() {
        super.show();
        open(super.content);
    }

    @Override
    public void hide() {
        super.hide();
        root.flow().remove(super.content.getParent());
        root.flow().remove(this.foreground);
        foreground.restyle(null, root);
    }

    @Override
    public Dialog effect() {
        this.wrapperEffect = WrapperEffect.GRAY;
        return this;
    }


}
