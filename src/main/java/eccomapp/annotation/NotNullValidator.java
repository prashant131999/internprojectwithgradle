package eccomapp.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullValidator implements
        ConstraintValidator<NotNullAnnotation,String> {
    @Override
    public void initialize(NotNullAnnotation constraintAnnotation) {}
    @Override
    public boolean isValid(String name, ConstraintValidatorContext cxt) {

        return name != null && name.matches("^[a-zA-Z]*$");
    }
}
