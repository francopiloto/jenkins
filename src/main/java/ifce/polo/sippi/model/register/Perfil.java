package ifce.polo.sippi.model.register;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import ifce.polo.sippi.util.UID;
import lombok.Setter;

@Setter
@MappedSuperclass
public abstract class Perfil
{
    private Long id;

    private Timestamp dataCriacao;
    private Timestamp dataModificacao;
    private Timestamp dataHomologacao;

    private boolean ativado;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @CreationTimestamp
    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @UpdateTimestamp
    public Timestamp getDataModificacao() {
        return dataModificacao;
    }

/* --------------------------------------------------------------------------------------------- */

    public Timestamp getDataHomologacao() {
        return dataHomologacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public boolean isAtivado() {
        return ativado;
    }

/* --------------------------------------------------------------------------------------------- */

    @Transient
    public abstract UID getUID();

/* --------------------------------------------------------------------------------------------- */

}
