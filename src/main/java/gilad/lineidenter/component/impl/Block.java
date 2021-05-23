package gilad.lineidenter.component.impl;

import gilad.lineidenter.component.Identable;

import java.util.List;
import java.util.stream.Collectors;

/*
* composite
* */
public class Block implements Identable {

    private final List<Identable> content;

    public Block(List<Identable> content) {
        this.content = content;
    }

    @Override
    public String asString() {
        return content.stream()
                .map(Identable::asString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
