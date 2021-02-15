package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NotNull
@Size(min = 5, max = 20)

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = {})
public @interface Username
{
    String message() default "username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
