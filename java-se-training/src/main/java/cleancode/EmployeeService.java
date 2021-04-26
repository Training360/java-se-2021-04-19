package cleancode;

public class EmployeeService {

    public static void main(String[] args) {
        var employee = new Employee();

        employee.getAddresses().add(new Address("Budapest"));

        employee.numberOfAddresses();
    }
}
