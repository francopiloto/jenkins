package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ifce.polo.sippi.service.validation.LattesValidator;

@Flag("lattes")

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = LattesValidator.class)
public @interface Lattes 
{
    boolean required() default true;

    String message() default "lattes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
