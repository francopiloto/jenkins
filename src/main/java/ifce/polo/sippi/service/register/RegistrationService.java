package ifce.polo.sippi.service.register;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.model.register.Perfil;

public interface RegistrationService<P extends Perfil>
{
    public static final String NOTIFICATION_GROUP = "cadastro";

/* --------------------------------------------------------------------------------------------- */

    public String getManagedProfileName();

/* --------------------------------------------------------------------------------------------- */

    public FormMetadata getFormMetadata();

/* --------------------------------------------------------------------------------------------- */

    public Availability checkAvailability(String id);

/* --------------------------------------------------------------------------------------------- */

    public AsyncValidationResult validateAsync(String property, String value);

/* --------------------------------------------------------------------------------------------- */

    public ObjectNode saveRegistrationRequest(P profile);

/* --------------------------------------------------------------------------------------------- */

    public void confirmRegistration(Long profileId, boolean accept);

/* --------------------------------------------------------------------------------------------- */

}
