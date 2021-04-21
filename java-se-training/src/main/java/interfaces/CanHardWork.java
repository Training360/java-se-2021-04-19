package interfaces;

public interface CanHardWork {

    default void doWorkForMoney() {
        System.out.println("can hard work");
    }
}
