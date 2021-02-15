package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NotNull
@Size(min = 5, max = 16)
@Pattern(regexp = "[0-9a-zA-Z!@#$%^&*]")
@Mask(value = "[0-9a-zA-Z!@#$%^&*]", regex = true)

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = {})
public @interface Password
{
	String equalTo() default "";
	
    String message() default "password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
