package ifce.polo.sippi.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ValidationService
{
    @Autowired private ObjectMapper mapper;
    @Autowired private SpringValidatorAdapter validator;

/* --------------------------------------------------------------------------------------------- */

    public ObjectNode generateRules(Object target) {
        return generateRules(target, null);
    }

/* --------------------------------------------------------------------------------------------- */

    public ObjectNode generateRules(Object target, String[] include) {
        return new ConstraintMapper(mapper, target, include).execute();
    }

/* --------------------------------------------------------------------------------------------- */

    public ObjectNode validate(Object target) {
        return validate(target, null);
    }

/* --------------------------------------------------------------------------------------------- */

    public ObjectNode validate(Object target, CustomFieldValidator customValidator)
    {
        BindingResult result = new BeanPropertyBindingResult(target, "");
        validator.validate(target, result);

        if (customValidator != null)
        {
            Context context = new Context()
            {
                public void addError(String objectName, String field, Object rejectedValue, String ...codes) {
                    result.addError(new FieldError(objectName, field, rejectedValue, false, codes, null, null));
                }
            };

            customValidator.validate(context);
        }

        if (!result.hasErrors()) {
            return null;
        }

        ObjectNode rejected = mapper.createObjectNode();

        for (FieldError error : result.getFieldErrors())
        {
            String[] tokens = error.getField().split("[.]");
            ObjectNode node = rejected;

            for (int i = 0; i < tokens.length; ++i)
            {
                if (i < tokens.length - 1)
                {
                    if (node.has(tokens[i])) {
                        node = (ObjectNode) node.get(tokens[i]);
                    }
                    else {
                        node = node.putObject(tokens[i]);
                    }
                }
                else
                {
                    Object rejectedValue = error.getRejectedValue();
                    String code = error.getCode();

                    node = node.putObject(tokens[i]);
                    node.put("value", rejectedValue != null ? rejectedValue.toString() : null);

                    if ("unique".equalsIgnoreCase(code)) {
                        node.put("code", code);
                    }
                }
            }
        }

        return rejected;
    }

/* --------------------------------------------------------------------------------------------- */

    public boolean isValid(Class<?> definition, String propertyName, Object value) {
        return validator.validateValue(definition, propertyName, value).isEmpty();
    }

/* --------------------------------------------------------------------------------------------- */

    public interface CustomFieldValidator {
        public void validate(Context context);
    }

/* --------------------------------------------------------------------------------------------- */

    public interface Context {
        public void addError(String objectName, String field, Object rejectedValue, String ...codes);
    }

/* --------------------------------------------------------------------------------------------- */

}
