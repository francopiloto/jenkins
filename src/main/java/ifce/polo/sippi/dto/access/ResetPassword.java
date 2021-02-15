package ifce.polo.sippi.dto.access;

import ifce.polo.sippi.annotation.CPF;
import ifce.polo.sippi.annotation.Required;
import lombok.Setter;

public class ResetPassword
{
    @Setter private String cpf;

/* --------------------------------------------------------------------------------------------- */

    @Required
    @CPF
    public String getCpf() {
        return cpf;
    }

/* --------------------------------------------------------------------------------------------- */

}
