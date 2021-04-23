package training.employees;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        var parts = value.split(" ");
        return parts.length >= 2 && Arrays.stream(parts).allMatch(s -> Character.isUpperCase(s.charAt(0)));
    }
}
