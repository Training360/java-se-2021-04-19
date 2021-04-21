package interfaces;

public interface CanWork {

    void doWork();

    /**
     * @since 1.2
     */
    default void doWorkForMoney() {
        System.out.println("can work");
    }

}
