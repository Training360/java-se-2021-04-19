package composite;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Employee {

    private String name;

    private List<Phone> phones;

//    public boolean hasPhoneNumber(String prefix) {
//        return phones.stream()
//                .anyMatch(p -> p.getNumber().startsWith(prefix));
//    }
}
