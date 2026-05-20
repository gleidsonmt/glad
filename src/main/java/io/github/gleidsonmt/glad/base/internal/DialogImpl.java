

package io.github.gleidsonmt.glad.base.internal;

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

        if (this.with != null) {
            root.getChildren().add(foreground.restyle(with, root));
        }

        root.flow()
                .pos(pos)
//                .width(width == -1 ? 600 : width)
//                .height(height == -1 ? 400 : height)
                .width(width.get())
                .height(height.get())
                .anchor(anchor)
                .content(new DialogContainer(node))
                .show();

        reset();
    }

    private void reset() {
        with = null;
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
        this.with = WrapperEffect.GRAY;
        return this;
    }


}
