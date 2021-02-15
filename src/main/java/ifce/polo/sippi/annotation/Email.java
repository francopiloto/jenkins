package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ifce.polo.sippi.service.validation.EmailValidator;

@Size(min = 5, max = 80)
@Pattern(regexp = ".+[@].+[.].+")
@Flag("email")

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = EmailValidator.class)
public @interface Email
{
    boolean required() default true;

    String message() default "email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
