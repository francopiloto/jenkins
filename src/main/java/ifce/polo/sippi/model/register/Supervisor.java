package ifce.polo.sippi.model.register;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import ifce.polo.sippi.annotation.Email;
import ifce.polo.sippi.annotation.ItemList;
import lombok.Setter;

@Entity
@Setter
public class Supervisor extends PessoaPerfil
{
    private String emailNotificacao;
    private Collection<String> tiposNotificacao;

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Email
    public String getEmailNotificacao() {
        return emailNotificacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @ElementCollection
    @CollectionTable(name = "supervisor_notificacao", joinColumns = @JoinColumn(name = "supervisor"))
    @Column(name="notificacao", length = 40)
    @ItemList(min = 0)
    public Collection<String> getTiposNotificacao() {
        return tiposNotificacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String toString() {
        return getPessoa().getNome();
    }

/* --------------------------------------------------------------------------------------------- */

}
