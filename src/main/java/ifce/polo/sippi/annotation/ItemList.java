package ifce.polo.sippi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ifce.polo.sippi.service.validation.ItemListValidator;

@Retention(RUNTIME)
@Target({ METHOD })
@Constraint(validatedBy = ItemListValidator.class)
public @interface ItemList
{
    int min() default 1;
    int max() default 0;

    String message() default "itemlist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
