package ifce.polo.sippi.service.access;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import ifce.polo.sippi.dto.access.AccessBadge;
import ifce.polo.sippi.util.UID;

public interface AccessProvider
{

/* --------------------------------------------------------------------------------------------- */

    public String getManagedProfileName();

/* --------------------------------------------------------------------------------------------- */

    public List<AccessBadge> getAccessBadges(UID uid);

/* --------------------------------------------------------------------------------------------- */

    public Collection<? extends GrantedAuthority> getAuthorities();

/* --------------------------------------------------------------------------------------------- */

    public boolean authorize(Long pessoaId, Long profileId);

/* --------------------------------------------------------------------------------------------- */

    public int getPriority();

/* --------------------------------------------------------------------------------------------- */

}
