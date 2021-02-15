package ifce.polo.sippi.service.register;

import static ifce.polo.sippi.util.StringUtils.underlineCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.exception.RegistrationException;
import ifce.polo.sippi.model.register.Perfil;
import ifce.polo.sippi.service.register.Availability.Status;
import ifce.polo.sippi.service.validation.ValidationService;
import ifce.polo.sippi.service.validation.ValidationService.Context;
import ifce.polo.sippi.service.validation.ValidationService.CustomFieldValidator;
import ifce.polo.sippi.util.Option;

@Service
public abstract class AbstractRegistrationService<P extends Perfil> implements RegistrationService<P>
{
    @Autowired protected ValidationService validationService;
    @Autowired private ObjectMapper jsonMapper;

    private Class<P> profileClass;
    private final String profileName;

/* --------------------------------------------------------------------------------------------- */

    public AbstractRegistrationService(Class<P> profileClass)
    {
        this.profileClass = profileClass;
        this.profileName = underlineCase(profileClass.getSimpleName());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String getManagedProfileName() {
        return profileName;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public FormMetadata getFormMetadata()
    {
        FormMetadata metadata = new FormMetadata();
        metadata.setRules(validationService.generateRules(profileClass));

        ObjectNode options = jsonMapper.createObjectNode();
        addMetadataOptions(new Options(options));

        if (options.size() > 0) {
            metadata.setOptions(options);
        }

        return metadata;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transactional
    public ObjectNode saveRegistrationRequest(P profile)
    {
        ObjectNode errors = validationService.validate(profile, new CustomFieldValidator()
        {
            public void validate(Context context) {
                preSaveCustomValidation(profile, context);
            }
        });

        Availability availability = checkAvailability(profile);

        if (availability.getStatus() != Status.AVAILABLE) {
            throw new RegistrationException(availability.getStatus().value);
        }

        if (errors != null) {
            return errors;
        }

        save(profile);
        return null;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public AsyncValidationResult validateAsync(String property, String value) {
        return AsyncValidationResult.AVAILABLE;
    }

/* --------------------------------------------------------------------------------------------- */

    protected void addMetadataOptions(Options options) {}

/* --------------------------------------------------------------------------------------------- */

    protected void preSaveCustomValidation(P profile, Context context) {}

/* --------------------------------------------------------------------------------------------- */

    protected abstract Availability checkAvailability(P profile);

/* --------------------------------------------------------------------------------------------- */

    protected abstract void save(P profile);

/* --------------------------------------------------------------------------------------------- */

    protected static class Options
    {
        private final ObjectNode options;

        private Options(ObjectNode options) {
            this.options = options;
        }

        public <T extends Option> void add(String fieldName, Iterable<T> data)
        {
            ArrayNode list = options.putArray(fieldName);

            for (Option option : data) {
                list.addObject().put("text", option.getText()).put("value", option.getValue());
            }
        }
    }

/* --------------------------------------------------------------------------------------------- */

}
