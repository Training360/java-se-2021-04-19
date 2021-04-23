package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeeMapper employeeMapper;

    private final AtomicLong idGenerator = new AtomicLong();

    private final List<Employee> employees = new ArrayList<>(
            List.of(
                    new Employee(idGenerator.incrementAndGet(), "John Doe"),
                    new Employee(idGenerator.incrementAndGet(), "Jane Doe")
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
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .peek(e -> e.setName(command.getName()))
                .map(employeeMapper::toDto)
                .findAny().orElseThrow(() -> new EmployeeNotFoundException("Not found with id: " + id));
    }

    public void deleteById(long id) {
        var deleted = employees.removeIf(e -> e.getId() == id);
        if (!deleted) {
            throw new EmployeeNotFoundException("Not found with id: " + id);
        }
    }
}
