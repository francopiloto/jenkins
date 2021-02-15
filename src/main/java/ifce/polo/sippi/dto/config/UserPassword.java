package ifce.polo.sippi.dto.config;


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
   
    public String getNewPassword() {
        return newPassword;
    }

/* --------------------------------------------------------------------------------------------- */

    @Password
   
    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

/* --------------------------------------------------------------------------------------------- */    
    
}
