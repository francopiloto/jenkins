package ifce.polo.sippi.model.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CaracterizacaoPerguntas
{
	private Short id;
	private String pergunta;
		
/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */
    
    @NotNull
    @Size(min = 1, max = 255)
    public String getPergunta() {
    	return pergunta;
    }

/* --------------------------------------------------------------------------------------------- */    
    
    public void setId(Short id) {
        this.id = id;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
    
/* --------------------------------------------------------------------------------------------- */
    
}