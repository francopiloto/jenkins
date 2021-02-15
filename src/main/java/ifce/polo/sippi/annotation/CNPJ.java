package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ifce.polo.sippi.service.validation.CNPJValidator;

@Pattern(regexp = "([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})")
@Mask("00.000.000/0000-00")
@Size(min = 1, max = 18)
@Flag("cnpj")

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = CNPJValidator.class)
public @interface CNPJ
{
    boolean required() default true;

    String message() default "cnpj";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
