package ifce.polo.sippi.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ifce.polo.sippi.model.access.Usuario;

public class UserCredentials implements UserDetails
{
    private static final long serialVersionUID = 1L;

    private final Usuario usuario;

    private Collection<? extends GrantedAuthority> authorities;
    private String profileName;
    private Long profileId;
    
/* --------------------------------------------------------------------------------------------- */

    public UserCredentials(Usuario usuario)
    {
        this.usuario = usuario;
        this.authorities = new ArrayList<>();
    }

/* --------------------------------------------------------------------------------------------- */

    public void setProfile(String name, Long id, Collection<? extends GrantedAuthority> authorities)
    {
        profileName = name;
        profileId = id;

        this.authorities = authorities != null ? authorities : new ArrayList<>();
    }

/* --------------------------------------------------------------------------------------------- */

    public void update(Usuario usuario)
    {
        UserCredentials principal = new UserCredentials(usuario);

        principal.profileName = profileName;
        principal.profileId = profileId;

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();

        auth = new UsernamePasswordAuthenticationToken(principal,
                auth.getCredentials(), auth.getAuthorities());

        securityContext.setAuthentication(auth);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String getUsername() {
        return usuario.getNome();
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean isEnabled() {
        return usuario.isAtivado();
    }

/* --------------------------------------------------------------------------------------------- */

    public Long getUserId() {
        return usuario.getId();
    }

/* --------------------------------------------------------------------------------------------- */

    public boolean isAdmin() {
        return "admin".contentEquals(usuario.getNome());
    }

/* --------------------------------------------------------------------------------------------- */

    public String getPersonName() {
        return isAdmin() ? "admin" : usuario.getPessoa().getNome();
    }

/* --------------------------------------------------------------------------------------------- */

    public Long getPersonId() {
        return isAdmin() ? null : usuario.getPessoa().getId();
    }

/* --------------------------------------------------------------------------------------------- */

    public String getProfileName() {
        return profileName;
    }

/* --------------------------------------------------------------------------------------------- */

    public Long getProfileId() {
        return profileId;
    }

/* --------------------------------------------------------------------------------------------- */

}
