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
import ifce.polo.sippi.annotation.URL;
import ifce.polo.sippi.annotation.UpperCase;
import ifce.polo.sippi.model.Arquivo;
import lombok.Setter;

@Entity
@Setter
public class Pesquisador extends PessoaPerfil
{
    private String siape;
    private Campus campus;
    private String lattes;
    private String areaFormacao;
    private Titulacao titulacao;
    private List<AreaPesquisa> areaPesquisa;
    private List<Arquivo> arquivos;

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Mask("0#")
    @Size(min = 1, max = 20)
    @Column(unique = true)
    @AsyncValidation
    public String getSiape() {
        return siape;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @ManyToOne
    public Campus getCampus() {
        return campus;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @URL
    @Lattes
    public String getLattes() {
        return lattes;
    }

/* --------------------------------------------------------------------------------------------- */

    @UpperCase
    public String getAreaFormacao() {
        return areaFormacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @ManyToOne
    public Titulacao getTitulacao() {
        return titulacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany
    @JoinTable(name               = "pesquisador_area",
               joinColumns        = @JoinColumn(name = "pesquisador_id"),
               inverseJoinColumns = @JoinColumn(name = "area_id"))
    @ItemList
    public List<AreaPesquisa> getAreaPesquisa() {
        return areaPesquisa;
    }

/* --------------------------------------------------------------------------------------------- */

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "pesquisador_arquivo",
               joinColumns        = @JoinColumn(name = "pesquisador_id"),
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
