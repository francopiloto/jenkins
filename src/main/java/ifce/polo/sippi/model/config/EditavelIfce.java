package ifce.polo.sippi.model.config;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.PhoneNumber;
import ifce.polo.sippi.model.register.Endereco;
import lombok.Setter;

@Entity
@Setter
public class EditavelIfce 
{
	private Short id;
    private String telefone;
    private String assinatura;
    private String cargo;
    private Endereco endereco;
    
/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @PhoneNumber
    public String getTelefone() {
        return telefone;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 100)
    public String getAssinatura() {
        return assinatura;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 80)
    public String getCargo() {
        return cargo;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public Endereco getEndereco() {
        return endereco;
    }

/* --------------------------------------------------------------------------------------------- */

    public void copy(EditavelIfce editavelIFCE)
    {
        id = editavelIFCE.id;
        telefone = editavelIFCE.telefone;
        assinatura = editavelIFCE.assinatura;
        endereco = editavelIFCE.endereco;
        cargo = editavelIFCE.cargo;
    }

/* --------------------------------------------------------------------------------------------- */

}
