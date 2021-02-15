package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

@Size(min = 1, max = 255)

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = {})
public @interface Name
{
    String message() default "name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
