package ifce.polo.sippi.model.config;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ifce.polo.sippi.annotation.Email;
import ifce.polo.sippi.annotation.Mask;
import lombok.Setter;

@Entity
@Setter
public class EmailConfig
{
    private Short id;
    private String servidor;
    private String usuario;
    private String senha;
    private String protocolo;
    private String codificacao;
    private Integer porta;
    private boolean autenticado = true;
    private boolean tls = true;

/* --------------------------------------------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Short getId() {
        return id;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 80)
    public String getServidor() {
        return servidor;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Email
    @Size(min = 1, max = 80)
    public String getUsuario() {
        return usuario;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 80)
    public String getSenha() {
        return senha;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 20)
    public String getProtocolo() {
        return protocolo;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Size(min = 1, max = 20)
    public String getCodificacao() {
        return codificacao;
    }

/* --------------------------------------------------------------------------------------------- */

    @NotNull
    @Mask("0#")
    public Integer getPorta() {
        return porta;
    }

/* --------------------------------------------------------------------------------------------- */

    public boolean isAutenticado() {
        return autenticado;
    }

/* --------------------------------------------------------------------------------------------- */

    public boolean isTls() {
        return tls;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setId(Short id) {
        this.id = id;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setSenha(String senha) {
        this.senha = senha;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setCodificacao(String codificacao) {
        this.codificacao = codificacao;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

/* --------------------------------------------------------------------------------------------- */

    public void setTls(boolean tls) {
        this.tls = tls;
    }

/* --------------------------------------------------------------------------------------------- */

    public void copy(EmailConfig emailConfig)
    {
        id = emailConfig.id;
        servidor = emailConfig.servidor;
        usuario = emailConfig.usuario;
        senha = emailConfig.senha;
        protocolo = emailConfig.protocolo;
        codificacao = emailConfig.codificacao;
        porta = emailConfig.porta;
        autenticado = emailConfig.autenticado;
        tls = emailConfig.tls;
    }

/* --------------------------------------------------------------------------------------------- */

}

