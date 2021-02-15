package ifce.polo.sippi.dto.config;

import ifce.polo.sippi.annotation.EqualTo;
import ifce.polo.sippi.annotation.NotEqualTo;
import ifce.polo.sippi.annotation.Password;
import lombok.Setter;

@Setter
public class UserPassword 
{
	private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;

/* --------------------------------------------------------------------------------------------- */

    @Password
    public String getCurrentPassword() {
        return currentPassword;
    }

/* --------------------------------------------------------------------------------------------- */

    @Password
    @NotEqualTo("currentPassword")
    public String getNewPassword() {
        return newPassword;
    }

/* --------------------------------------------------------------------------------------------- */

    @Password
    @EqualTo("newPassword")
    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

/* --------------------------------------------------------------------------------------------- */    
    
}
