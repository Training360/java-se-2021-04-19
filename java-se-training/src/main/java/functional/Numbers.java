package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Numbers {

    public List<Integer> calculate(List<Integer> numbers, BinaryOperator<Integer> operator) {
        if (numbers == null || numbers.isEmpty() || isEvenSize(numbers)) {
            throw new IllegalArgumentException("Invalid list (empty or not even)");
        }
        List<Integer> result = new ArrayList<>();
        // Prefereáljuk a for each
        for (int i = 0; i < numbers.size(); i += 2) {
            int x = numbers.get(i);
            int y = numbers.get(i + 1);
            int resultOfOperator = operator.apply(x, y);
            result.add(resultOfOperator);
        }
        return result;
    }

    private boolean isEvenSize(List<Integer> numbers) {
        return numbers.size() % 2 != 0;
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 3, 4, 2, 5, 1, 6, 4);
        System.out.println(new Numbers().calculate(numbers, Numbers::add));
        System.out.println(new Numbers().calculate(numbers, Numbers::multiple));
    }

    public static Integer add(Integer x, Integer y) {
        return x + y;
    }

    public static Integer multiple(Integer x, Integer y) {
        return x * y;
    }
}
