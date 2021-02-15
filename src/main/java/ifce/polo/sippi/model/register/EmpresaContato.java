package ifce.polo.sippi.model.register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.Email;
import ifce.polo.sippi.annotation.Name;
import ifce.polo.sippi.annotation.PhoneNumber;
import lombok.Setter;

@Entity
@Setter
public class EmpresaContato
{
    private Long id;
    private String nome;
    private String cargo;
    private String email;
    private String telefone;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Name
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @Size(min = 1, max = 50)
    public String getCargo() {
        return cargo;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

/* --------------------------------------------------------------------------------------------- */

    @PhoneNumber
    public String getTelefone() {
        return telefone;
    }

/* --------------------------------------------------------------------------------------------- */

}
