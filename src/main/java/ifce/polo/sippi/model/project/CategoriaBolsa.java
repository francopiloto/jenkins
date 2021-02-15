package ifce.polo.sippi.model.project;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.Currency;
import ifce.polo.sippi.util.TableList;

@Entity
public class CategoriaBolsa implements TableList
{
    private Short id;
    private String nome;
    private BigDecimal valorMaximo;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 255)
    @Column(unique = true)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Currency
    public BigDecimal getValorMaximo() {
        return valorMaximo;
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

    public void setValorMaximo(BigDecimal valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

}
