package counter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CounterResult {

    private final int positive;

    private final int negative;

    public CounterResult() {
        positive = 0;
        negative = 0;
    }

    public CounterResult addNumber(int n) {
        var positive = this.positive;
        var negative = this.negative;
        if (n < 0) {
            negative ++;
        }
        else if (n > 0) {
            positive ++;
        }

        return new CounterResult(positive, negative);
    }

    public CounterResult addCounterResult(CounterResult another) {
        return new CounterResult(
                this.positive + another.positive,
                this.negative + another.negative
        );
    }


}
