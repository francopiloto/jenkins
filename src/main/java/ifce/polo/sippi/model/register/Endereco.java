package ifce.polo.sippi.model.register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.Mask;
import ifce.polo.sippi.annotation.UpperCase;
import lombok.Setter;

@Entity
@Setter
public class Endereco
{
    private Long id;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;

    private boolean enabled = true;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Pattern(regexp = "([0-9]{5}[-]?[0-9]{3})|([0-9]{8})")
    @Mask("00000-000")
    @Size(min = 1, max = 16)
    public String getCep() {
        return cep;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 100)
    @UpperCase
    public String getLogradouro() {
        return logradouro;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 16)
    @Pattern(regexp = "[A-Za-z0-9 ]+")
    @Mask(value = "[A-Za-z0-9]+", regex = true)
    @UpperCase
    public String getNumero() {
        return numero;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 50)
    @UpperCase
    public String getBairro() {
        return bairro;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 50)
    public String getCidade() {
        return cidade;
    }

/* --------------------------------------------------------------------------------------------- */

    @Size(min = 1, max = 50)
    @UpperCase
    public String getComplemento() {
        return complemento;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(max = 2)
    public String getUf() {
        return uf;
    }

/* --------------------------------------------------------------------------------------------- */

    @Transient
    public boolean isEnabled() {
        return enabled;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(logradouro).append(' ').append(numero).append(", ");

        if (bairro != null) {
            sb.append(bairro).append(", ");
        }

        sb.append(cidade).append('-').append(uf).append(", CEP: ").append(cep);

        if (complemento != null) {
            sb.append(", ").append(complemento);
        }

        return sb.toString();
    }

/* --------------------------------------------------------------------------------------------- */

}
