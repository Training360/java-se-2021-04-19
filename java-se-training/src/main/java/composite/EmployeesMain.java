package composite;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeesMain {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Joe",
                        List.of(
                                new Phone(List.of("36", "30", "1234567")),
                                new Phone(List.of("36", "70", "7234567")))),
                new Employee("Jack",
                        List.of(
                                new Phone(List.of("36", "30", "1234599")),
                                new Phone(List.of("36", "70", "7234599"))))

                );

        //? employee
        //? String, int, sum(), Phone
        // Összes telefonszám
//        List<Phone> phones = employees.stream()
//                .flatMap(e -> e.getPhones().stream())
//                .filter(p -> p.getNumber().startsWith("30"))
//                .collect(Collectors.toList());

//        List<Employee> filtered =
//                employees.stream()
//                        .filter(e -> e.hasPhoneNumber("30"))
//                .collect(Collectors.toList());



        List<String> parts =
                employees.stream()
                .flatMap(e -> e.getPhones().stream())
                .flatMap(p -> p.getParts().stream())
                        .filter(s -> s.length() > 5)
                .collect(Collectors.toList());

        System.out.println(parts);
    }


}
