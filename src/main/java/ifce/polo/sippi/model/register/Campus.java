package ifce.polo.sippi.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.util.Option;
import ifce.polo.sippi.util.TableList;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Setter
@EqualsAndHashCode
public class Campus implements TableList, Option
{
    private Short id;
    private String nome;
    private boolean ativado;

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

    @Override
    public String toString() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public String getText() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public Short getValue() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    public boolean isAtivado() {
        return ativado;
    }
    
/* --------------------------------------------------------------------------------------------- */

}
