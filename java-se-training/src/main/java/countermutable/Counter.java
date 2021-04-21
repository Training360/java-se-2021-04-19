package countermutable;

import java.util.List;

public class Counter {

    public CounterResult countNumbers(List<Integer> numbers) {
        //reduce(new CounterResult(), )  // immutable
        //collect(() -> CounterResult(),) // mutable

//        return numbers.stream()
//                .collect(
//                        CounterResult::new,
//                        CounterResult::addNumber,
//                        CounterResult::addCounterResult
//                );

        return numbers.stream()
                .collect(
                        CounterResult.collect()
                );
    }

    public static void main(String[] args) {
        System.out.println(new Counter().countNumbers(List.of(1, 0, 2, 3, 0, -5, -6, 0, -8, 3, 6)));
    }
}
