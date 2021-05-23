package gilad.lineidenter.component;

import gilad.lineidenter.component.impl.BaseLine;
import gilad.lineidenter.component.impl.Block;
import gilad.lineidenter.component.impl.SubLine;
import gilad.lineidenter.model.NumberedIdentation;

import java.util.*;

public class IdentableFactory {

    public static List<Identable> get(List<Map.Entry<String, Integer>> wordsToLeadingWsCount) {

        int prevWsCount = 0;
        List<Identable> results = new ArrayList<>();
        List<Identable> blockParts = new ArrayList<>();
        NumberedIdentation numberedIdentation = new NumberedIdentation();

        for (Map.Entry<String, Integer> entry : wordsToLeadingWsCount) {
            int wsDelta = entry.getValue() - prevWsCount;
            //TODO consider enums where the action code resides
            if (wsDelta == 0) { //use next number
                numberedIdentation.incLast();
                blockParts.add(new BaseLine(numberedIdentation.get(), entry.getKey(), entry.getValue()));
            } else if (wsDelta > 0) {   //subline
                blockParts.add(new SubLine(numberedIdentation.get(), entry.getKey(), entry.getValue(), wsDelta));
                numberedIdentation.addKLast(wsDelta, 1);
            } else { //end of block
                if (!blockParts.isEmpty()) {
                    results.add(new Block(blockParts));
                    blockParts = new ArrayList<>();
                }

                numberedIdentation.removeLastNDigits(Math.abs(wsDelta));
                numberedIdentation.incLast();

                blockParts.add(new BaseLine(numberedIdentation.get(), entry.getKey(), entry.getValue()));
            }

            prevWsCount = entry.getValue();
        }
        if (!blockParts.isEmpty()) {
            results.add(new Block(blockParts));
        }
        return results;
    }
}
