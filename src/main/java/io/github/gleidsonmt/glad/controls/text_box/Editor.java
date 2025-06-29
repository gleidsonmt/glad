/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.glad.controls.text_box;

import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.controls.skin.EditorSkin;
import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

/**
 * Basic class for editor boxes.
 * Editor is a wrapper of TextField it aims to be used inside a control.
 * Usually a box control, like (PasswordBox,  {@link TextBox}) etc.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/09/2022
 */
@SuppressWarnings("unused")
@DefaultProperty("children")
public class Editor extends TextField {

    // used for password text
    private final BooleanProperty maskText = new SimpleBooleanProperty(false);
    // used for max text input
    private final IntegerProperty max = new SimpleIntegerProperty(Integer.MAX_VALUE);

    public Editor() {
        getStyleClass().add("editor");
        setAlignment(Pos.CENTER_LEFT);

        addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (getLength() >= max.get()) {
                positionCaret(getLength());
                event.consume();
            }
        });
    }

    @Override
    public void paste() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();

        if (clipboard.hasString()) {
            final String text = clipboard.getString();
            if (text != null) {
                int major = text.length();
                int comparator = max.get() - this.getLength();
                String sub;
                if ((major + this.getLength()) < max.get()) {
                    //
                    this.replaceSelection(text);
                } else {
                    sub = text.substring(0,
                            comparator);
                    this.replaceSelection(sub);
                }
            }
        }
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new EditorSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(Resources.getAgent("editor.css"));
    }

    public boolean isMaskText() {
        return maskText.get();
    }

    public BooleanProperty maskTextProperty() {
        return maskText;
    }

    public void setMaskText(boolean maskText) {
        this.maskText.set(maskText);
    }

    public int getMaxText() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void setMaxText(int max) {
        this.max.set(max);
    }
}
