package cloud;

public class PayPeriodMain {

    public static void main(String[] args) {
        PayPeriod period = PayPeriod.ANNUAL;
        System.out.println(period.getLength());


        System.out.println(PayPeriod.LIFETIME.getLength());
    }
}
