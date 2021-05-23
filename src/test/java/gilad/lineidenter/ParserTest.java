package gilad.lineidenter;

import gilad.lineidenter.component.Identable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/*
 * basic end to end tests
 * */
public class ParserTest {

    private Parser parser = new Parser();

    @Test
    void parseFile_when_file_exists_expect_its_idented_correctly() throws IOException {

        List<Identable> identables = parser.parseFile(Paths.get("testFile.txt"));

        Assertions.assertTrue(identables.size() > 0);

        String actualOutput = identables.stream()
                .map(Identable::asString)
                .collect(Collectors.joining(System.lineSeparator()));

        Assertions.assertEquals("1. this is the line" + System.lineSeparator() +
                " 1.1. this is the line" + System.lineSeparator() +
                "  1.1.1. this is the line" + System.lineSeparator() +
                "  1.1.2. this is the line" + System.lineSeparator() +
                "2. this is the line" + System.lineSeparator() +
                " 2.1. this is the line" + System.lineSeparator() +
                " 2.2. this is the line", actualOutput);
    }
}
