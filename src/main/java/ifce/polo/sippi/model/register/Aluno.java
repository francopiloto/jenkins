package ifce.polo.sippi.model.register;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import ifce.polo.sippi.annotation.AsyncValidation;
import ifce.polo.sippi.annotation.FileList;
import ifce.polo.sippi.annotation.ItemList;
import ifce.polo.sippi.annotation.Lattes;
import ifce.polo.sippi.annotation.Mask;
import ifce.polo.sippi.annotation.Required;
import ifce.polo.sippi.annotation.URL;
import ifce.polo.sippi.annotation.UpperCase;
import ifce.polo.sippi.model.Arquivo;
import lombok.Setter;

@Entity
@Setter
public class Aluno extends PessoaPerfil
{
    private String matricula;
    private String curso;
    private InstituicaoEnsino instituicao;
    private String lattes;

    private NivelCurso nivelCurso;
    private List<AreaPesquisa> areaPesquisa;
    private List<Arquivo> arquivos;

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Mask("0#")
    @Pattern(regexp = "\\d+")
    @Size(min = 1, max = 20)
    @Column(unique = true)
    @AsyncValidation
    public String getMatricula() {
        return matricula;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 80)
    @UpperCase
    public String getCurso() {
        return curso;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @ManyToOne
    @Required
    public InstituicaoEnsino getInstituicao() {
        return instituicao;
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    @NotNull
    @ManyToOne
    public NivelCurso getNivelCurso() {
        return nivelCurso;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @ColumnDefault("http://lattes.cnpq.br/")
    @URL
    @Lattes
    public String getLattes() {
        return lattes;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany
    @JoinTable(name               = "aluno_area",
               joinColumns        = @JoinColumn(name = "aluno_id"),
               inverseJoinColumns = @JoinColumn(name = "area_id"))
    @ItemList
    public List<AreaPesquisa> getAreaPesquisa() {
        return areaPesquisa;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "aluno_arquivo",
               joinColumns        = @JoinColumn(name = "aluno_id"),
               inverseJoinColumns = @JoinColumn(name = "arquivo_id"))
    @FileList
    public List<Arquivo> getArquivos() {
        return arquivos;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public String getTitulacaoString() {
        return getNivelCurso().getTitulo();
    }

/* --------------------------------------------------------------------------------------------- */

}
