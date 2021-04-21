package interfaces;

public interface CanProgram extends CanWork {

    default void doWorkForMoney() {
        System.out.println("can program");
    }

}
