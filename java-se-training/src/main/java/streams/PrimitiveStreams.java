package streams;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveStreams {

    public static void main(String[] args) {
        IntStream.of(1, 3, 4, 7, 8, 10)
            .map(i -> i * 2)
        .forEach(System.out::println);

        IntStream.range(1, 6)
                .map(i -> i * i)
                .forEach(System.out::println);

        List.of("john doe", "jack", "jane smith").stream() // Stream<String>
                .mapToInt(String::length) // IntStream
                .mapToObj(i -> "Name length" + i)
                .forEach(System.out::println);

        var stat = IntStream.range(1, 6).summaryStatistics();
        System.out.printf("Min: %d, max: %d\n", stat.getMin(), stat.getMax());

        String s = IntStream.range(1, 6)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(":"));
        System.out.println(s);

        Set<Integer> numbers =
                IntStream.of(2,2,2,2,3,4,5,5,5)
                        .mapToObj(i -> i) // Stream<Integer>
                .collect(Collectors.toSet());
        System.out.println(numbers);


    }
}
