package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CountLetters {

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("letters.txt"))) {

            int count = (int) lines
                    .flatMapToInt(s -> s.chars())
                    .filter(i -> i == 'w')
                    .count();
            System.out.println(count);

        }
        catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }
}
