package gilad.lineidenter.component.impl;

import org.apache.commons.lang3.StringUtils;

/*
* leaf
* */
public class SubLine extends BaseLine {

    private static final String SEPARATOR = ".";

    private final int wsCountDelta;

    public SubLine(String prefix, String content, int wsCount, int wsCountDelta) {
        super(prefix, content, wsCount);
        this.wsCountDelta = wsCountDelta;
    }

    @Override
    public String asString() {
        return String.format("%s%s.%s. %s"
                , " ".repeat(wsCount)
                , prefix
                , StringUtils.repeat(Integer.toString(1), SEPARATOR, wsCountDelta)
                , content);
    }
}
