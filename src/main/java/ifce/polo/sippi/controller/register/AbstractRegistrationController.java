package ifce.polo.sippi.controller.register;


import static ifce.polo.sippi.util.StringUtils.getInitials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.model.register.Perfil;
import ifce.polo.sippi.service.register.Availability;
import ifce.polo.sippi.service.register.RegistrationService;
import ifce.polo.sippi.service.validation.ValidationService;

public abstract class AbstractRegistrationController<P extends Perfil, S extends RegistrationService<P>>
    extends AbstractController
{
    @Autowired protected ValidationService validationService;
    @Autowired protected RegistrationService<P> registrationService;

/* --------------------------------------------------------------------------------------------- */

    public ResponseEntity<?> getMetadata() {
        return ok(registrationService.getFormMetadata());
    }
    
/* --------------------------------------------------------------------------------------------- */

    public ResponseEntity<?> checkAvailability(String id)
    {
        if (id == null) {
            return badRequest("Missing required parameters");
        }

        Availability availability = registrationService.checkAvailability(id);
        ObjectNode body = json().put("status", availability.getStatus().value);

        if (availability.getStatus() == Availability.Status.INVALID) {
            body.putObject("rejected").putObject("id").put("value", id);
        }

        if (availability.getId() != null) {
            body.put("id", availability.getId()).put("initials", getInitials(availability.getName()));
        }

        return ok(body);
    }

/* --------------------------------------------------------------------------------------------- */

    public ResponseEntity<?> validateAsync(String property, String value)
    {
        if (property == null || value == null) {
            return badRequest("Missing required query parameters");
        }

        switch (registrationService.validateAsync(property, value))
        {
            case AVAILABLE:
                return ok(success());

            case INVALID:
                return ok(failed().put("rule", "rejected").put("value", value));

            case TAKEN:
                return ok(failed().put("rule", "unique"));

            default:
                return badRequest("Invalid property");
        }
    }

/* --------------------------------------------------------------------------------------------- */

    public ResponseEntity<?> doRegistration(P profile)
    {
        ObjectNode error = registrationService.saveRegistrationRequest(profile);

        if (error != null) {
            return validationFailed(error);
        }

        return created(null);
    }

/* --------------------------------------------------------------------------------------------- */

}
