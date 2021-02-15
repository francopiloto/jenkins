package ifce.polo.sippi.service.access;

import static ifce.polo.sippi.util.StringUtils.underlineCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ifce.polo.sippi.dto.access.AccessBadge;
import ifce.polo.sippi.model.access.Permissao;
import ifce.polo.sippi.model.access.UsuarioPerfil;
import ifce.polo.sippi.model.register.PessoaPerfil;
import ifce.polo.sippi.repository.access.UserAccessRepository;
import ifce.polo.sippi.repository.access.UsuarioPerfilRepository;
import ifce.polo.sippi.repository.register.PessoaPerfilRepository;
import ifce.polo.sippi.util.UID;

public abstract class ProfileAccessProvider<P extends PessoaPerfil, R extends PessoaPerfilRepository<P>>
    implements AccessProvider
{
    @Autowired private R profileRepository;
    @Autowired private UserAccessRepository userAccessRepository;
    @Autowired private UsuarioPerfilRepository usuarioPerfilRepository;

    private final String credentialColumnName;
    private final String profileName;

/* --------------------------------------------------------------------------------------------- */

    public ProfileAccessProvider(Class<P> profileClass, String credentialColumnName)
    {
        this.credentialColumnName = credentialColumnName;
        this.profileName = underlineCase(profileClass.getSimpleName());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String getManagedProfileName() {
        return profileName;
    }

/* --------------------------------------------------------------------------------------------- */

    public List<AccessBadge> getAccessBadges(UID uid) {
        return userAccessRepository.loadProfileBadges(uid, getManagedProfileName(), credentialColumnName, true);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        UsuarioPerfil perfil = usuarioPerfilRepository.findById(getManagedProfileName()).orElse(null);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Permissao permissao : perfil.getPermissoes()) {
            authorities.add(new SimpleGrantedAuthority(permissao.getNome()));
        }

        return authorities;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean authorize(Long pessoaId, Long profileId)
    {
        P profile = profileRepository.findById(profileId).orElse(null);
        return profile != null && profile.getPessoa().getId().equals(pessoaId);
    }

/* --------------------------------------------------------------------------------------------- */

}
