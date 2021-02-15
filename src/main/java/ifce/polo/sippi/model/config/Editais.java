package ifce.polo.sippi.model.config;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import ifce.polo.sippi.annotation.URL;
import ifce.polo.sippi.model.Arquivo;
import lombok.Setter;

@Entity
@Setter
public class Editais
{
	private Short id;
	
	private Arquivo arquivo;
	private String link;	
    private String perfil;
    private boolean ifce;

/* --------------------------------------------------------------------------------------------- */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }
    
/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public Arquivo getArquivo() {
        return arquivo;
    }
    
/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @URL
    public String getLink() {
        return link;
    }
    
/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public String getPerfil() {
        return perfil;
    }
    
/* --------------------------------------------------------------------------------------------- */

    @NotNull
    public Boolean isIfce() {
        return ifce;
    }    

/* --------------------------------------------------------------------------------------------- */

}
