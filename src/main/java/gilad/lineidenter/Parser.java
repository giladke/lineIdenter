package gilad.lineidenter;

import gilad.lineidenter.component.Identable;
import gilad.lineidenter.component.IdentableFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    public List<Identable> parseFile(Path file) throws IOException {

        List<Map.Entry<String, Integer>> linesToWsCount = Files.readAllLines(file).stream()
                .map(line ->new AbstractMap.SimpleImmutableEntry<>(line.trim(), countLeadingWhitespace(line)))
                .collect(Collectors.toList());

        if (linesToWsCount.isEmpty()) {
            System.out.println("empty file - nothing to show");
            System.exit(0);
        }

        return IdentableFactory.get(linesToWsCount);
    }

    private List<Map.Entry<String, Integer>> mapByWhitespaceCount(List<String> lines) {
        List<Map.Entry<String, Integer>> linesToWsCount = new ArrayList<>();
        for (String line : lines) {
            linesToWsCount.add(
                    new AbstractMap.SimpleImmutableEntry<>(line.trim(), countLeadingWhitespace(line))
            );
        }
        return linesToWsCount;
    }

    private static int countLeadingWhitespace(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isWhitespace(line.charAt(i))) {
                break;
            }
            count++;
        }
        return count;
    }
}
