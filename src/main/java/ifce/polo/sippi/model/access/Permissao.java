package ifce.polo.sippi.model.access;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Permissao
{
    private String nome;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @Size(min = 4, max = 20)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setNome(String nome) {
        this.nome = nome;
    }

/* --------------------------------------------------------------------------------------------- */

}
