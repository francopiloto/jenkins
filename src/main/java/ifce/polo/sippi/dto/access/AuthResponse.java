package ifce.polo.sippi.dto.access;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter @Setter
public class AuthResponse
{
    private String token;
    private String name;
    private List<AccessBadge> profiles;
}
