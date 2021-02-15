package ifce.polo.sippi.model.project;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import ifce.polo.sippi.annotation.Currency;
import ifce.polo.sippi.util.TableList;

@Entity
public class VinculoTrabalho implements TableList
{
    private Short id;
    private String nome;
    private BigDecimal percentual;
    private BigDecimal fixo;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 30)
    @Column(unique = true)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Currency
    public BigDecimal getPercentual() {
        return percentual != null ? percentual : BigDecimal.ZERO;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Currency
    @ColumnDefault("0")
    public BigDecimal getFixo() {
        return fixo != null ? fixo : BigDecimal.ZERO;
    }

/* --------------------------------------------------------------------------------------------- */

    @Transient
    public String getData()
    {
        return new StringBuilder().append(getPercentual()).append(',').append(getFixo()).append(',')
                .append(nome.equalsIgnoreCase("BOLSA")).toString();
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

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setFixo(BigDecimal fixo) {
        this.fixo = fixo;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

}
