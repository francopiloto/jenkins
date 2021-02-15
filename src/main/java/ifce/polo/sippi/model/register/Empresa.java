package ifce.polo.sippi.model.register;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.CNPJ;
import ifce.polo.sippi.annotation.Mask;
import ifce.polo.sippi.annotation.UpperCase;
import ifce.polo.sippi.util.UID;
import lombok.Setter;

@Entity
@Setter
public class Empresa extends Perfil
{
    private String razaoSocial;
    private String cnpj;
    private String cnae;

    private Endereco endereco;
    private List<EmpresaContato> contatos;

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 100)
    @UpperCase
    public String getRazaoSocial() {
        return razaoSocial;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @CNPJ
    @Column(unique = true)
    public String getCnpj() {
        return cnpj;
    }

/* --------------------------------------------------------------------------------------------- */

    @Size(min = 1, max = 20)
    @Pattern(regexp = "([0-9]{2}[.]?[0-9]{2}[-]?[0-9])|([0-9]{5})")
    @Mask("00.00-0")
    public String getCnae() {
        return cnae;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Valid
    public Endereco getEndereco() {
        return endereco;
    }

/* --------------------------------------------------------------------------------------------- */

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    @Valid
    public List<EmpresaContato> getContatos() {
        return contatos;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public UID getUID()
    {
        Long id = getId();
        return id != null ? new UID(id, "empresa") : null;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return razaoSocial;
    }

/* --------------------------------------------------------------------------------------------- */

}
