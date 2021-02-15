package ifce.polo.sippi.model.project;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PerguntaAvaliacaoCoordenador
{
	private Short id;
	private String pergunta;
	
	private Collection<String> opcoes;
	
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

    @ElementCollection
    @CollectionTable(name = "pergunta_coordenador_opcao", joinColumns = @JoinColumn(name = "pergunta_id"))
    @Column(name="opcao", length = 200)
    public Collection<String> getOpcoes() {
        return opcoes;
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
    
    public void setOpcoes(Collection<String> opcoes) {
        this.opcoes = opcoes;
    }
    
/* --------------------------------------------------------------------------------------------- */

}
