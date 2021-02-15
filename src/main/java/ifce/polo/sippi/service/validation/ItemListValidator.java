package ifce.polo.sippi.service.validation;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ifce.polo.sippi.annotation.ItemList;

public class ItemListValidator implements ConstraintValidator<ItemList, Collection<?>>
{
    private ItemList annotation;

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(ItemList constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean isValid(Collection<?> value, ConstraintValidatorContext context)
    {
        int min = annotation.min();
        int max = annotation.max();

        context.disableDefaultConstraintViolation();

        if (value == null)
        {
            if (min > 0)
            {
                context.buildConstraintViolationWithTemplate("required").addConstraintViolation();
                return false;
            }

            return true;
        }

        if (min > 0 && value.size() < min)
        {
            context.buildConstraintViolationWithTemplate("min").addConstraintViolation();
            return false;
        }

        if (max > 0 && value.size() > max)
        {
            context.buildConstraintViolationWithTemplate("max").addConstraintViolation();
            return false;
        }

        return true;
    }

/* --------------------------------------------------------------------------------------------- */

}
