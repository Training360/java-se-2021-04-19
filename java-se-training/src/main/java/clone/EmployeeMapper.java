package clone;

import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(Employee employee);
}
