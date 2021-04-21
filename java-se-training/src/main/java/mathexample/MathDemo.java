package mathexample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MathDemo {

    public static void main(String[] args) {
        //Path p = Path.of("letters.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(Path.of("letters.txt").toFile()))) {

        }
        catch (IOException ioe) {
            //throw
        }


        //String content = Files.readString(Path.of("letters.txt"));

        int value = Integer.MAX_VALUE;
        System.out.println(value);
//        value += 30;
//        System.out.println(value);

//        Math.addExact(value, 30);

        Map<String, Integer> values = Map.of("a", 1, "b", 2, "c", 3);

        List<String> result = List.of("John Doe", "Jane Doe").stream().collect(Collectors.toUnmodifiableList());
//        result.add("Jack Doe");

        Optional<String> optionalString = Optional.of("apple");

        // Clean code szabály: lehetőleg ne használjunk feltételekben negációt!
        String s = "alma";
        if (s.isBlank()) {

        }

        String fruit = null;
//        if (!fruit.equals("apple")) { // NullPointerException
//
//        }

        if (!"apple".equals(fruit)) { // false

        }

        System.out.println("alma".repeat(10));


    }


}
