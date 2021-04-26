package training.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@Slf4j
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }

    @GetMapping("{id}")
    public EmployeeDto getEmployeeById(@PathVariable long id) {
        return employeesService.findEmployeeById(id);
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        log.info("Create employee" + command);
        var dto = employeesService.createEmployee(command);
        var entity = ResponseEntity
                .created(URI.create("/api/employees/" + dto.getId()))
                .header("request-id", UUID.randomUUID().toString())
                .body(dto);

        return entity;
    }

    @PutMapping("{id}")
    public EmployeeDto updateEmployee(@PathVariable long id, @Valid @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id) {
        employeesService.deleteById(id);
    }
}
