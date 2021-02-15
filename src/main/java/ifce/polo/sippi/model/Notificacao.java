package ifce.polo.sippi.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Notificacao
{
    private Long id;
    private String tipo;
    private String descricao;
    private Long referencia;
    private Timestamp data;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(max = 40)
    public String getTipo() {
        return tipo;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(max = 255)
    public String getDescricao() {
        return descricao;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public Long getReferencia() {
        return referencia;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public Timestamp getData() {
        return data;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setId(Long id) {
        this.id = id;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setReferencia(Long referencia) {
        this.referencia = referencia;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setData(Timestamp data) {
        this.data = data;
    }

/* --------------------------------------------------------------------------------------------- */

}
