package counter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Counter {

    public CounterResult countNumbers(List<Integer> numbers) {

        // szelekció, összegzés tétele
        // accumulator antipattern
        // párhuzamosíthatóság: NEM párhuzamosítható
        // EGY ADATTAL dolgozik, nem adatok csoportjával

//        int positive = 0; // global mutable state! - baj - több szál módosítja
//        for (var number: numbers) {
//            if (number > 0) {
//                positive++;
//            }
//        }

        // Használjuk reduction operátort
        // egyszerű
        // flexibilis
        // párhuzamosítható
        // magasszintű

        // Kát féle módon: reduce, collect

//        // Számolást, count()
//        int result = numbers.stream()
//                .reduce(
//                        0,
//                        (temp, n) -> temp + 1,
//                        (temp1, temp2) -> temp1 + temp2
//                );
//        System.out.println(result);

        // Számolást, sum()
//        int result = numbers.stream()
//                .reduce(
//                        0,
//                        (temp, n) -> temp + n,
//                        (temp1, temp2) -> temp1 + temp2
//                );
//        System.out.println(result);

//        Set<Integer> result = numbers.stream()
//                .collect(
//                    () -> new HashSet<>(),
//                        (temp, n) -> temp.add(n),
//                        (temp1, temp2) -> temp1.addAll(temp2)
//                );
//        System.out.println(result);

//        Set<Integer> result = numbers.stream()
//                .collect(
//                        HashSet::new,
//                        HashSet::add,
//                        HashSet::addAll
//                );
//        System.out.println(result);

//        return  numbers.stream()
//                .reduce(
//                        new CounterResult(),
//                        (temp, n) -> {
//                            if (n < 0) {
//                                return new CounterResult(temp.getPositive(), temp.getNegative() + 1);
//                            }
//                            else if (n >  0) {
//                                return new CounterResult(temp.getPositive() + 1, temp.getNegative());
//                            }
//                            else {
//                                return temp;
//                            }
//                        },
//                        (temp1, temp2) -> {
//                            return new CounterResult(temp1.getPositive() + temp2.getPositive(),
//                                    temp1.getNegative() + temp2.getNegative());
//                        });

        // reduce és a collect között?
        // reduce - immutable
        // collect - mutable
        return numbers.stream()
                .reduce(
                        new CounterResult(),
                        CounterResult::addNumber,
                        CounterResult::addCounterResult
                );
    }

    public static void main(String[] args) {
        System.out.println(new Counter().countNumbers(List.of(1, 0, 2, 3, 0, -5, -6, 0, -8, 3, 6)));
    }
}
