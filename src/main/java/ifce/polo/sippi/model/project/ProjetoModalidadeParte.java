package ifce.polo.sippi.model.project;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.Mask;
import ifce.polo.sippi.util.Identifiable;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Setter
@EqualsAndHashCode
public class ProjetoModalidadeParte implements Identifiable<Integer>
{
    private Integer id;

    private String nome;
    private BigDecimal minimo;
    private BigDecimal maximo;
    private Byte prioridade;
    private BigDecimal percentualSuporte;

    private boolean empresa;
    private boolean multiValorado;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 40)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    @Mask(value = "990,00", reverse = true)
    public BigDecimal getMinimo() {
        return minimo;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    @Mask(value = "990,00", reverse = true)
    public BigDecimal getMaximo() {
        return maximo;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public Byte getPrioridade() {
        return prioridade;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    @Mask(value = "990,00", reverse = true)
    public BigDecimal getPercentualSuporte() {
        return percentualSuporte;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public boolean isEmpresa() {
        return empresa;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public boolean isMultiValorado() {
        return multiValorado;
    }

/* --------------------------------------------------------------------------------------------- */

}
