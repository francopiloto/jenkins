package ifce.polo.sippi.dto.access;

import ifce.polo.sippi.annotation.Password;
import ifce.polo.sippi.annotation.Username;
import lombok.Setter;

@Setter
public class LoginRequest
{
    private String username;
    private String password;

/* --------------------------------------------------------------------------------------------- */

    @Username
    public String getUsername() {
        return username;
    }

/* --------------------------------------------------------------------------------------------- */

    @Password
    public String getPassword() {
        return password;
    }

/* --------------------------------------------------------------------------------------------- */

}
