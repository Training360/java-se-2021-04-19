package countermutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

// Mutable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterResult {

    private int positive;

    private int negative;

    public void addNumber(Integer n) {
        if (n < 0) {
            negative ++;
        }
        else if (n > 0) {
            positive ++;
        }
    }

    public void addCounterResult(CounterResult another) {
                this.positive += another.positive;
                this.negative += another.negative;
    }

    public static Collector<Integer, CounterResult, CounterResult> collect() {
        return new Collector() {
            @Override
            public Supplier<CounterResult> supplier() {
                return CounterResult::new;
            }

            @Override
            public BiConsumer<CounterResult, Integer> accumulator() {
                return (t, a) -> t.addNumber(a);
            }

            @Override
            public BinaryOperator<CounterResult> combiner() {
                return (temp1, temp2) -> {temp1.addCounterResult(temp2); return temp1;};
            }

            @Override
            public Function<CounterResult, CounterResult> finisher() {
                return Function.identity();
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Set.of(Characteristics.UNORDERED, Characteristics.CONCURRENT);
            }
        };
    }

}
