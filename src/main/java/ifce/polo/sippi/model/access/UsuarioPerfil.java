package ifce.polo.sippi.model.access;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class UsuarioPerfil
{
    private String nome;
    private Collection<Permissao> permissoes;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @Size(min = 4, max = 20)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany
    @JoinTable(name               = "usuario_perfil_permissao",
               joinColumns        = @JoinColumn(name = "usuario_perfil"),
               inverseJoinColumns = @JoinColumn(name = "permissao"))
    public Collection<Permissao> getPermissoes() {
        return permissoes;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setNome(String nome) {
        this.nome = nome;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setPermissoes(Collection<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

/* --------------------------------------------------------------------------------------------- */

}
