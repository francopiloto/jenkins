package ifce.polo.sippi.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ifce.polo.sippi.annotation.Lattes;

public class LattesValidator implements ConstraintValidator<Lattes, String>
{
	
    private Lattes constraintAnnotation;

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(Lattes constraintAnnotation) {
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
        	return value.matches("^https?:\\/\\/lattes\\.cnpq\\.br\\/\\d+$");
        }
        catch (Exception e) {}

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("validation.lattes")
            .addConstraintViolation();


		return false;
	}
	
/* --------------------------------------------------------------------------------------------- */

}