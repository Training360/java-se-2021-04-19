package functional;

import java.util.List;
import java.util.function.Function;

public class Products {

    public int sum(List<Product> products, Function<Product, Integer> function) {
        int result = 0;
        for (var product: products) {
            int tempPrice = function.apply(product);
            result += tempPrice;
        }
        return result;
    }

    public static void main(String[] args) {
        var products = List.of(
                new Product(2, 1),
                new Product(4, 3),
                new Product(5, 4));

        System.out.println(new Products().sum(products,
                Product::getPrice
                ));

        System.out.println(new Products().sum(products,
                Product::getBonusPrice
        ));
    }
}
