package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemo {

    public static void main(String[] args) {
        List<String> names = List.of("John Doe", "    ", "Jane Doe", "      ", "Jack Doe");

//        Stream<String> namesStream = names.stream();
//        Stream<String> simpleNamesStream = namesStream
//                .flatMap(s -> s.isBlank() ? Stream.empty() : Arrays.stream(s.split(" ")));
//
//        simpleNamesStream.forEach(System.out::println);

        names.stream()
                .filter(s -> !s.isBlank())
                .flatMap(s ->Arrays.stream(s.split(" ")))
                .map(s -> s.toUpperCase())
                .skip(2)
                .peek(System.out::println)
                .limit(2)
                .forEach(System.out::println);

//        List<Employee> employees = List.of(new Employee("John Doe"), new Employee("Jane Doe"));
//
//        List<Employee> result = employees
//                .stream()
//                .map(e -> new Employee(e.getName() + " Grrr"))
//                .collect(Collectors.toList());


//        System.out.println(result);

    }
}
