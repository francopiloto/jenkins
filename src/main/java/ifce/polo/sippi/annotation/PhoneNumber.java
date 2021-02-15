package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Size(min = 1, max = 15)
@Pattern(regexp = "\\(\\d{2}\\)[ ]\\d{4,5}-\\d{4}")
@Mask("phone")

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = {})
public @interface PhoneNumber
{
    String message() default "phone";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
