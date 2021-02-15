package ifce.polo.sippi.repository.register;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ifce.polo.sippi.dto.register.ProfileRegisterNotification;

@NoRepositoryBean
public interface PerfilRepository<Profile> extends JpaRepository<Profile, Long>
{

/* --------------------------------------------------------------------------------------------- */

    public Page<ProfileRegisterNotification> findPending(boolean ativado, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
