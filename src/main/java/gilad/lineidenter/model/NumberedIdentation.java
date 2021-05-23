package gilad.lineidenter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumberedIdentation {

    private final List<Integer> content;

    public NumberedIdentation() {
        this.content = new ArrayList<>();
        this.content.add(0);
    }

    public String get() {
        return content.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("."));
    }

    public void addKLast(int count, int digit) {
        if(count < 0){
            throw new IllegalArgumentException("provided count must be non negative");
        }
        for(int i=0; i<count; i++) {
            content.add(digit);
        }
    }

    public void incLast() {
        if (content.isEmpty()) {
            throw new IllegalStateException("cannot increase last when nothing to increase");
        }

        Integer last = content.get(content.size() - 1);
        content.set(content.size() - 1, last + 1);
    }

    public void removeLastNDigits(int numOfDigits) {
        if (numOfDigits == 0) {
            return;
        }

        if (numOfDigits < 0 || content.size() < numOfDigits) {
            throw new IllegalArgumentException("illegal value");
        }

        for (; numOfDigits > 0; numOfDigits--) {
            content.remove(content.size() - 1);
        }
    }

}
