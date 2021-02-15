package ifce.polo.sippi.model.project;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import ifce.polo.sippi.annotation.Mask;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Setter
@EqualsAndHashCode(exclude = {"nome", "timeoutLimit", "ativado"})
public class ProjetoModalidade
{
    private Short id;

    private String nome;
    private BigDecimal suporteOperacionalFinanceiro;
    private BigDecimal suporteOperacionalTotal;
    private BigDecimal infraestrutura;
    private Byte tipoSuporte;
    private int timeoutLimit;
    private boolean ativado;

    private List<ProjetoModalidadeParte> partes;

/* --------------------------------------------------------------------------------------------- */

    public ProjetoModalidade()
    {
        suporteOperacionalTotal = new BigDecimal("10.00");
        infraestrutura = new BigDecimal("5.00");
        timeoutLimit = 10;
    }

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 40)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("49.00")
    @Mask(value = "90,00", reverse = true)
    public BigDecimal getSuporteOperacionalFinanceiro() {
        return suporteOperacionalFinanceiro;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("50.00")
    @Mask(value = "90,00", reverse = true)
    public BigDecimal getSuporteOperacionalTotal() {
        return suporteOperacionalTotal;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("49.00")
    @Mask(value = "90,00", reverse = true)
    public BigDecimal getInfraestrutura() {
        return infraestrutura;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public Byte getTipoSuporte() {
        return tipoSuporte;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Min(1)
    @Max(99)
    @Mask("90")
    @ColumnDefault("10")
    public int getTimeoutLimit() {
        return timeoutLimit;
    }

/* --------------------------------------------------------------------------------------------- */

    public boolean isAtivado() {
        return ativado;
    }

/* --------------------------------------------------------------------------------------------- */

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_modalidade", nullable = false)
    @Valid
    public List<ProjetoModalidadeParte> getPartes()
    {
        if (partes == null || partes.isEmpty()) {
            return partes;
        }

        Collections.sort(partes, new Comparator<ProjetoModalidadeParte>()
        {
            @Override
            public int compare(ProjetoModalidadeParte o1, ProjetoModalidadeParte o2)
            {
                if (o1.getId() == null || o2.getId() == null) {
                    return 0;
                }

                return o1.getId().compareTo(o2.getId());
            }
        });

        return partes;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

}
