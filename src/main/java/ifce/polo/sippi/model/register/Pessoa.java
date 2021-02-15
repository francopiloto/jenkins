package ifce.polo.sippi.model.register;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import ifce.polo.sippi.annotation.CPF;
import ifce.polo.sippi.annotation.Email;
import ifce.polo.sippi.annotation.Name;
import ifce.polo.sippi.annotation.PhoneNumber;
import ifce.polo.sippi.annotation.UpperCase;
import ifce.polo.sippi.util.Identifiable;
import lombok.Setter;

@Entity
@Setter
public class Pessoa implements Identifiable<Long>
{
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    private Endereco endereco;
    private boolean ativado;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Name
    @UpperCase
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @CPF
    @Column(unique = true)
    public String getCpf() {
        return cpf;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @PhoneNumber
    public String getTelefone() {
        return telefone;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public Endereco getEndereco() {
        return endereco;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public boolean isAtivado() {
        return ativado;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

}
