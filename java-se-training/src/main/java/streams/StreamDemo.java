package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
//        Stream<Double> doubles = //Stream.generate(StreamDemo::random);
//                Stream.iterate(2.0, d -> d + 2);
//        doubles.limit(10).forEach(System.out::println);

        List<Integer> numbers = List.of(1, 4, 3, 7, 3, 9, 4, 8, 2);
        int i = numbers.stream().max(Comparator.naturalOrder()).get();
        System.out.println(i);

        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.parallel().min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println); // ape
    }

    public static Double random() {
        System.out.println("Generate random number");
        return Math.random();
    }
}
