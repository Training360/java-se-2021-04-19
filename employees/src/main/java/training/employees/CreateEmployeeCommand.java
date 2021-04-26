package training.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @Schema(description = "the name of the employee", example = "John Doe")
    //@NotBlank
    @ValidName
    private String name;
}
