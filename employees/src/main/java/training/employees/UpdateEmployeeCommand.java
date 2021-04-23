package training.employees;

import lombok.Data;

@Data
public class UpdateEmployeeCommand {

    @ValidName
    private String name;
}
