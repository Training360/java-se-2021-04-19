package clone;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;

import java.util.List;

@Mapper(mappingControl = DeepClone.class)
public interface EmployeeCloningMapper {

    Employee clone(Employee employee);

    List<Employee> clone(List<Employee> employees);
}
