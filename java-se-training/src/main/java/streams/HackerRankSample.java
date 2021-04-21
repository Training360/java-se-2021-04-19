package streams;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HackerRankSample {

    public static void main(String[] args) {
        int n = 2;

//        for (var i = 0; i < 10; i ++) {
//            System.out.printf("%d x %d = %d\n", n, i + 1, (i + 1) * n);
//        }

        String result = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> String.format("%d x %d = %d\n", n, i, i * n))
                .collect(Collectors.joining());
        System.out.println(result);

//        int result = IntStream
//                .iterate(2, i -> i + 2)
//                .limit(10)
//                .sum();
//        System.out.println(result);
    }
}
