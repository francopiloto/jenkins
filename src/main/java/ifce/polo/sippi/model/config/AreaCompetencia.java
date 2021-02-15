package ifce.polo.sippi.model.config;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Setter;

@Entity
@Setter
public class AreaCompetencia
{
    private Short id;
    private String nome;
    private List<SubArea> subAreas;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area")
    @Valid
    public List<SubArea> getSubAreas() {
		return subAreas;
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
