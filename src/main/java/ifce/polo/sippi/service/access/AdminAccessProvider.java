package ifce.polo.sippi.service.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import ifce.polo.sippi.dto.access.AccessBadge;
import ifce.polo.sippi.model.access.Permissao;
import ifce.polo.sippi.repository.access.PermissaoRepository;
import ifce.polo.sippi.security.SecurityConstants;
import ifce.polo.sippi.util.UID;

@Component
public class AdminAccessProvider implements AccessProvider
{
    private static final List<AccessBadge> badges = new ArrayList<AccessBadge>(0);

    @Autowired private PermissaoRepository permissaoRepository;

/* --------------------------------------------------------------------------------------------- */

    @Override
    public String getManagedProfileName() {
        return SecurityConstants.ADMIN;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public List<AccessBadge> getAccessBadges(UID uid) {
        return badges;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Permissao permissao : permissaoRepository.findAll()) {
            authorities.add(new SimpleGrantedAuthority(permissao.getNome()));
        }

        return authorities;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public boolean authorize(Long pessoaId, Long profileId) {
        return false;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public int getPriority() {
        return 0;
    }

/* --------------------------------------------------------------------------------------------- */

}
