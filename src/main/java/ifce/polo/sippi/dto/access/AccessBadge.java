package ifce.polo.sippi.dto.access;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
public class AccessBadge
{
    private String profileName;
    private String profileId;
    private Object registrationNumber;

/* --------------------------------------------------------------------------------------------- */

    public AccessBadge(String profileName, String profileId, Object registrationNumber)
    {
        this.profileName = profileName;
        this.profileId = profileId;
        this.registrationNumber = registrationNumber;
    }

/* --------------------------------------------------------------------------------------------- */

}
