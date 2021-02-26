package eccomapp.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullValidator implements
        ConstraintValidator<NotNullAnnotation,String> {
    @Override
    public void initialize(NotNullAnnotation constraintAnnotation) {}
    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null  && (contactField.length() > 8) && (contactField.length() < 14);
    }
}
