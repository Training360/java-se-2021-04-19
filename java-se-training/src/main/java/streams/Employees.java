package streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Employees {

    public static final String BOSS_POSITION_NAME = "boss";

    public List<String> findEmployees(List<Employee> employees) {
        return employees.stream()
                .filter(Employees::isBoss)
                .sorted(Comparator.comparing(Employee::getSalary))
                .map(Employee::getName)
                .collect(Collectors.toList());
                // 16-os Java: .toList();
    }

    private static boolean isBoss(Employee e) {
        return e.getPosition().equalsIgnoreCase(BOSS_POSITION_NAME);
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("John1", 10, "programmer"),
                new Employee("John2", 6, "boss"),
                new Employee("John3", 7, "programmer"),
                new Employee("John4", 4, "boss"),
                new Employee("John5", 3, "programmer")
                );

        System.out.println(new Employees().findEmployees(employees));
    }
}
