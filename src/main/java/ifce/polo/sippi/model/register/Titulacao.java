package ifce.polo.sippi.model.register;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.model.project.CategoriaBolsa;
import ifce.polo.sippi.util.Option;
import lombok.Setter;

@Entity
@Setter
public class Titulacao implements Option
{
    private Short id;
    private String valor;

    private List<CategoriaBolsa> bolsas;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 30)
    public String getValor() {
        return valor;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany
    @JoinTable(name               = "titulacao_bolsa",
               joinColumns        = @JoinColumn(name = "titulacao_id"),
               inverseJoinColumns = @JoinColumn(name = "categoria_bolsa_id"))
    public List<CategoriaBolsa> getBolsas() {
        return bolsas;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return valor;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public String getText() {
        return valor;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public Short getValue() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

}
