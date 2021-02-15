package ifce.polo.sippi.model.register;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import ifce.polo.sippi.util.UID;
import lombok.Setter;

@MappedSuperclass
@Setter
public abstract class PessoaPerfil extends Perfil
{
    private Pessoa pessoa;

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    public Pessoa getPessoa() {
        return pessoa;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Transient
    public UID getUID()
    {
        Long id = null;
        try { id = getPessoa().getId(); } catch (Exception e) {}

        return id != null ? new UID(id, "pessoa") : null;
    }

/* --------------------------------------------------------------------------------------------- */

    @Transient
    public String getTitulacaoString() {
        return null;
    }

/* --------------------------------------------------------------------------------------------- */

}
