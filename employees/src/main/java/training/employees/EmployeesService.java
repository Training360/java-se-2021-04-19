package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeeMapper employeeMapper;

    private final List<Employee> employees = new ArrayList<>(
            List.of(
                    new Employee(1L, "John Doe"),
                    new Employee(2L, "Jane Doe")
            )
    );

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return employees.stream()
                .filter(byName(prefix))
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    private Predicate<Employee> byName(Optional<String> prefix) {
        return e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase());
    }

    public EmployeeDto findEmployeeById(long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .map(employeeMapper::toDto)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id " + id));
    }
}
