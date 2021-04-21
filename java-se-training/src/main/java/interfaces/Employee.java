package interfaces;

public class Employee implements CanWork, CanHardWork {

    @Override
    public void doWork() {
        System.out.println("employee works");
    }

    @Override
    public void doWorkForMoney() {
        CanHardWork.super.doWorkForMoney();
    }

    public static void main(String[] args) {
        var employee = new Employee();
        employee.doWork();

        employee.doWorkForMoney();
    }
}
