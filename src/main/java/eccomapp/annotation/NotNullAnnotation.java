package eccomapp.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {  ElementType.FIELD })
@Constraint(validatedBy = NotNullValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullAnnotation {
      String message() default "This field should be contain any letter";
      Class<?>[] groups() default {};
      Class<? extends Payload>[] payload() default {};
}


