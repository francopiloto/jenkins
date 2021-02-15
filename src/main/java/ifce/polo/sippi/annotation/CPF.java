package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ifce.polo.sippi.service.validation.CPFValidator;

@Pattern(regexp="([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})")
@Mask("000.000.000-00")
@Size(min = 1, max = 14)
@Flag("cpf")

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = CPFValidator.class)
public @interface CPF
{
    boolean required() default true;

    String message() default "cpf";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
