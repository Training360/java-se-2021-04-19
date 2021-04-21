package streams;

import java.util.List;
import java.util.stream.Collectors;

public class GroupingExamples {

    public static void main(String[] args) {
        var names = List.of("John Doe", "Jack Doe", "Jane","Jane",  "Smith Smith", "Smith Jack", "Adam Bien");

        System.out.println(names.stream()
        .collect(Collectors.groupingBy(
                s -> s.substring(0, 1),
            Collectors.counting()
//                Collectors.joining("---")
        )));

    }
}
