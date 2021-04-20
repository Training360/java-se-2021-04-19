package cloud;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudStorage implements Comparable<CloudStorage> {

    public static final int MONTH_OF_YEAR = 12;
    public static final int GB_1000 = 1000;
    private String provider;

    private int space;

    private double price;

    private PayPeriod payPeriod;

    public CloudStorage(CloudStorage another) {
        provider = another.provider;
        space = another.space;
        price = another.price;
        payPeriod = another.payPeriod;
    }

    public double pricePerPeriod() {
        // price / payperiod.length * 12 / space * 1000
        return price / payPeriod.getLength() * MONTH_OF_YEAR / space * GB_1000;
    }

    @Override
    public int compareTo(CloudStorage another) {
        return (int) (pricePerPeriod() - another.pricePerPeriod());
    }
}
