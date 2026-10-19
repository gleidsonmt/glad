package io.github.gleidsonmt.glad.tail;

import io.github.gleidsonmt.glad.HotTools;

import java.util.function.UnaryOperator;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 19/10/2026
 */
public class Converter implements UnaryOperator<String> {

    private final boolean write;

    public Converter() {
        this.write = true;
    }

    public Converter(boolean write) {
        this.write = write;
    }

    @Override
    public String apply(String s) {
        if (write) HotTools.getWatcher().append(s);

//        component.getProperties().put("tailwind-watched", true);
        return new ClassConverter().clean(s);
    }
}
