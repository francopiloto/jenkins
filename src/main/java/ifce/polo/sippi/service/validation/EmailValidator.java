package ifce.polo.sippi.service.validation;

import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ifce.polo.sippi.annotation.Email;

public class EmailValidator implements ConstraintValidator<Email, String>
{
    private Email constraintAnnotation;

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(Email constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (value == null && !constraintAnnotation.required()) {
            return true;
        }

        try
        {
            InternetAddress address = new InternetAddress(value);
            address.validate();
            return true;
        }
        catch (Exception e) {}

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("validation.email")
            .addConstraintViolation();

        return false;
    }

/* --------------------------------------------------------------------------------------------- */

}
