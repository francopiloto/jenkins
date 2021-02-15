package ifce.polo.sippi.model.register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.util.Option;
import lombok.Setter;

@Entity
@Setter
public class InstituicaoEnsino implements Option
{
	private Short id;
	private String nome;
	private String sigla;
	private String uf;
	
/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }	

/* --------------------------------------------------------------------------------------------- */
    
    @NotNull
    @Size(min = 1, max = 200)
    public String getNome() {
    	return nome;
    }

/* --------------------------------------------------------------------------------------------- */
   
    @Size(min = 1, max = 20)
    public String getSigla() {
    	return sigla;
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    @NotNull
    @Size(min = 1, max = 2)
    public String getUf() {
    	return uf;
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
    
}
