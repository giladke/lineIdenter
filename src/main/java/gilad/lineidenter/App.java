package gilad.lineidenter;

import gilad.lineidenter.component.Identable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {

    private static final Parser PARSER = new Parser();

    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
        }

        Path file = Paths.get(args[0]);
        if (Files.notExists(file)) {
            System.err.println("invalid path provided: " + file.toAbsolutePath());
            System.exit(-1);
        }

        try {
            List<Identable> parsingResults = PARSER.parseFile(file);
            for (Identable identable: parsingResults){
                System.out.println(identable.asString());
            }

        } catch (IOException e) {
            System.err.println("error while processing file "+ file);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void usage() {
        System.err.println("usage: java -jar myjar.jar <filePath>");
        System.exit(-1);
    }
}
