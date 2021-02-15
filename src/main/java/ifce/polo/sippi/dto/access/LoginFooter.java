package ifce.polo.sippi.dto.access;

import ifce.polo.sippi.model.config.EditavelPolo;
import ifce.polo.sippi.model.register.Endereco;
import lombok.Getter;

@Getter
public class LoginFooter
{
    private String nome;
    private String endereco;
    private String telefone;

/* --------------------------------------------------------------------------------------------- */

    public LoginFooter(EditavelPolo config)
    {
        nome = config.getNome();

        Endereco endereco = config.getEndereco();

        this.endereco = new StringBuilder()
                .append(endereco.getLogradouro()).append(", ")
                .append(endereco.getNumero()).append(" - ")
                .append(endereco.getBairro()).append(' ')
                .append(endereco.getCidade()).append("/")
                .append(endereco.getUf())
                .toString();

        telefone = config.getTelefone();
    }

/* --------------------------------------------------------------------------------------------- */

}
