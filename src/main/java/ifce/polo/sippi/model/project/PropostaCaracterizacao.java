package ifce.polo.sippi.model.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PropostaCaracterizacao
{
	private Long id;
	private CaracterizacaoPerguntas formCaracterizacaoPergunta;
	private String resposta;
	
/* --------------------------------------------------------------------------------------------- */
	
	public PropostaCaracterizacao copy() 
	{
		PropostaCaracterizacao pc = new PropostaCaracterizacao();
		
		pc.formCaracterizacaoPergunta = formCaracterizacaoPergunta;
		pc.resposta = resposta;
		
		return pc;
	}

/* --------------------------------------------------------------------------------------------- */
    
    public PropostaCaracterizacao() {}
    
/* --------------------------------------------------------------------------------------------- */
    
    public PropostaCaracterizacao(CaracterizacaoPerguntas formCaracterizacaoPergunta) {
        this.formCaracterizacaoPergunta = formCaracterizacaoPergunta;
    }	

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */
    
    @NotNull
    @ManyToOne
    public CaracterizacaoPerguntas getFormCaracterizacaoPergunta() {
    	return formCaracterizacaoPergunta;
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    @Size(min = 1, max = 1000)
    public String getResposta() {
        return resposta;
    }

/* --------------------------------------------------------------------------------------------- */    
    
    public void setId(Long id) {
        this.id = id;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setFormCaracterizacaoPergunta(CaracterizacaoPerguntas formCaracterizacaoPergunta) {
        this.formCaracterizacaoPergunta = formCaracterizacaoPergunta;
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    @Override
    public String toString() 
    {
        return new StringBuilder()
                .append("pergunta_id:")
                .append(formCaracterizacaoPergunta.getId())
                .append('\n')
                .append("resposta:")
                .append(resposta)
                .toString();
    }
    
/* --------------------------------------------------------------------------------------------- */

}

