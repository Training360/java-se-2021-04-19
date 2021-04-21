package clone;

import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
//        Employee employee = new Employee(1L, "John Doe", 1980);
//
//        EmployeeMapperImpl mapper = new EmployeeMapperImpl();
//
//        EmployeeDto dto = mapper.toEmployeeDto(employee);
//
//        System.out.println(dto);

        var employees = List.of(new Employee(1L, "John Doe", 1980));

        var clone = new EmployeeCloningMapperImpl().clone(employees);

        clone.get(0).setName("Jack Doe");

        System.out.println(employees.get(0));
    }
}
