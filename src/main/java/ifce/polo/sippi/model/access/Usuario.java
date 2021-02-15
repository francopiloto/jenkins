package ifce.polo.sippi.model.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.Username;
import ifce.polo.sippi.model.register.Pessoa;

@Entity
public class Usuario
{
    private Long id;

    private String nome;
    private String senha;
    private Pessoa pessoa;

    private boolean ativado = true;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @Username
    @Column(unique = true)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 5, max = 100)
    public String getSenha() {
        return senha;
    }

/* --------------------------------------------------------------------------------------------- */

    @OneToOne(fetch = FetchType.LAZY)
    public Pessoa getPessoa() {
        return pessoa;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public boolean isAtivado() {
        return ativado;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setId(Long id) {
        this.id = id;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setNome(String nome) {
        this.nome = nome;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setSenha(String senha) {
        this.senha = senha;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

/* --------------------------------------------------------------------------------------------- */

}
