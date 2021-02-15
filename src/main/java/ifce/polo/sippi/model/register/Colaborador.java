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
import javax.validation.constraints.Size;

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
public class Colaborador extends PessoaPerfil
{
    private String lattes;
    private Titulacao titulacao;
    private String areaFormacao;

    private InstituicaoEnsino instituicao;
    private String siape;
    private Campus campus;

    private List<AreaPesquisa> areaPesquisa;
    private List<Arquivo> arquivos;

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @URL
    @Lattes
    public String getLattes() {
        return lattes;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @ManyToOne
    public Titulacao getTitulacao() {
        return titulacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @UpperCase
    public String getAreaFormacao() {
        return areaFormacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @ManyToOne
    @Required
    public InstituicaoEnsino getInstituicao() {
        return instituicao;
    }

/* --------------------------------------------------------------------------------------------- */

    @Mask("0#")
    @Size(min = 1, max = 20)
    @Column(unique = true)
    @AsyncValidation
    @Required
    public String getSiape() {
        return siape;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToOne
    @Required
    public Campus getCampus() {
        return campus;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany
    @JoinTable(name               = "colaborador_area",
               joinColumns        = @JoinColumn(name = "colaborador_id"),
               inverseJoinColumns = @JoinColumn(name = "area_id"))
    @ItemList
    public List<AreaPesquisa> getAreaPesquisa() {
        return areaPesquisa;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "colaborador_arquivo",
               joinColumns        = @JoinColumn(name = "colaborador_id"),
               inverseJoinColumns = @JoinColumn(name = "arquivo_id"))
    @FileList
    public List<Arquivo> getArquivos() {
        return arquivos;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public String getTitulacaoString() {
        return getTitulacao().toString();
    }

/* --------------------------------------------------------------------------------------------- */

}
