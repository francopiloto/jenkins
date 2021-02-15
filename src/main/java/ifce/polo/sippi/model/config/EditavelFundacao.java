package ifce.polo.sippi.model.config;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.CNPJ;
import ifce.polo.sippi.annotation.Mask;
import ifce.polo.sippi.annotation.Name;
import ifce.polo.sippi.annotation.PhoneNumber;
import ifce.polo.sippi.model.register.Endereco;
import lombok.Setter;

@Entity
@Setter
public class EditavelFundacao
{
    private Short id;

    private String nome;
    private Endereco endereco;
    private String cnpj;
    private String telefone;
    private String inscricaoEstadual;
    private String nomePresidente;
    private String saudacao;

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
    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public Endereco getEndereco() {
        return endereco;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @CNPJ
    @Column(unique = true)
    public String getCnpj() {
        return cnpj;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @PhoneNumber
    public String getTelefone() {
        return telefone;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Pattern(regexp = "([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{1})")
    @Mask("00.000.000-0")
    @Size(min = 1, max = 16)
    @Column(unique = true)
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Name
    @Size(min = 1, max = 80)
    public String getNomePresidente() {
        return nomePresidente;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 20)
    public String getSaudacao() {
        return saudacao;
    }

/* --------------------------------------------------------------------------------------------- */

    public void copy(EditavelFundacao editavelFundacao)
    {
        id = editavelFundacao.id;
        nome = editavelFundacao.nome;
        endereco = editavelFundacao.endereco;
        cnpj = editavelFundacao.cnpj;
        telefone = editavelFundacao.telefone;
        inscricaoEstadual = editavelFundacao.inscricaoEstadual;
        nomePresidente = editavelFundacao.nomePresidente;
        saudacao = editavelFundacao.saudacao;
    }

/* --------------------------------------------------------------------------------------------- */

}
