package cleancode;

public class Numbers {

    public static void main(String[] args) {
        int favouriteNumber = 6;
        if (isEven(favouriteNumber)) {
            printEven();
        }
    }

    private static void printEven() {
        System.out.println("Paros");
    }

    private static boolean isEven(int i) {
        return i % 2 == 0;
    }
}
