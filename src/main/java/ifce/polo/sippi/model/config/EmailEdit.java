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
public class EmailEdit 
{
	private Short id;
	private String categoria;
	private String assunto;
	private String mensagem;
	
/* --------------------------------------------------------------------------------------------- */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Short getId() {
		return id;
	}

/* --------------------------------------------------------------------------------------------- */
	
	@NotNull
    @Size(min = 1, max = 20)
	public String getCategoria() {
		return categoria;
	}

/* --------------------------------------------------------------------------------------------- */
	
	@NotNull
    @Size(min = 1, max = 255)
	public String getAssunto() {
		return assunto;
	}

/* --------------------------------------------------------------------------------------------- */
	
	@NotNull
    @Size(min = 1, max = 255)
	public String getMensagem() {
		return mensagem;
	}
	
/* --------------------------------------------------------------------------------------------- */
	
	public void copy(EmailEdit emailEdit)
    {
        id = emailEdit.id;
        categoria = emailEdit.categoria;
        assunto = emailEdit.assunto;
        mensagem = emailEdit.mensagem;     
    }
	
/* --------------------------------------------------------------------------------------------- */

}
