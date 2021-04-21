package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainerReader {

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("MOCK_DATA.csv"))) {
            List<Trainer> trainers =
                    lines.
                            skip(1)
                            .map(Trainer::parse)
                            .filter(t -> !t.getName().startsWith("S"))
                    .collect(Collectors.toList());
            System.out.println(trainers);
        }
        catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }
}
