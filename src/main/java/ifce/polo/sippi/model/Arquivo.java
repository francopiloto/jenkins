package ifce.polo.sippi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Arquivo
{
    private Long id;
    private String nome;
    private byte[] dados;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 255)
    public String getNome() {
        return nome;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(max = 5 * 1024 * 1024)
    public byte[] getDados() {
        return dados;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setId(Long id) {
        this.id = id;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setNome(String nome) {
        this.nome = nome;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

/* --------------------------------------------------------------------------------------------- */

    @Transient
    public long size() {
        return dados != null ? dados.length : 0;
    }

/* --------------------------------------------------------------------------------------------- */

}
