package ifce.polo.sippi.model.config;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Setter;

@Entity
@Setter
public class SubArea 
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
    @Size(min = 1, max = 70)
    public String getNome() {
        return nome;
    }
    
/* --------------------------------------------------------------------------------------------- */
  
    public boolean isAtivado() {
		return ativado;
	}

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

}
