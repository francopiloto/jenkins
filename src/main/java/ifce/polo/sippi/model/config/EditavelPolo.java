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

import ifce.polo.sippi.annotation.Name;
import ifce.polo.sippi.model.register.Endereco;

@Entity
public class EditavelPolo
{
    private Short id;
    private String nome;
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
    @Name
    @Size(min = 1, max = 80)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 35)
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

    public void setId(Short id) {
        this.id = id;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setNome(String nome) {
        this.nome = nome;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

/* --------------------------------------------------------------------------------------------- */

    public void copy(EditavelPolo editavelPolo)
    {
        id = editavelPolo.id;
        nome = editavelPolo.nome;
        telefone = editavelPolo.telefone;
        assinatura = editavelPolo.assinatura;
        endereco = editavelPolo.endereco;
        cargo = editavelPolo.cargo;
    }

/* --------------------------------------------------------------------------------------------- */

}
