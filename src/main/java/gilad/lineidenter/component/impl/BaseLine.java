package gilad.lineidenter.component.impl;

import gilad.lineidenter.component.Identable;

/*
* leaf
* */
public class BaseLine implements Identable {

    protected final String prefix;
    protected final String content;
    protected final int wsCount;

    public BaseLine(String prefix, String content, int wsCount) {
        this.prefix = prefix;
        this.content = content;
        this.wsCount = wsCount;
    }

    @Override
    public String asString() {
        return String.format("%s%s. %s", " ".repeat(wsCount), prefix, content);
    }
}
