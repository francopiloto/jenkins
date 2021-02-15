package ifce.polo.sippi.service.register;

import static ifce.polo.sippi.util.StringUtils.concat;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ifce.polo.sippi.dto.register.ProfileRegisterNotification;
import ifce.polo.sippi.model.register.PessoaPerfil;
import ifce.polo.sippi.repository.register.AreaPesquisaRepository;
import ifce.polo.sippi.repository.register.PessoaPerfilRepository;
import ifce.polo.sippi.service.notification.NotificationProvider;

public abstract class MembroRegistrationService<P extends PessoaPerfil,R extends PessoaPerfilRepository<P>>
    extends PessoaRegistrationService<P, R> implements NotificationProvider
{
    @Autowired private AreaPesquisaRepository areaPesquisaRepository;

    private final List<String> notificationTypes;

/* --------------------------------------------------------------------------------------------- */

    public MembroRegistrationService(Class<P> profileClass)
    {
        super(profileClass);
        notificationTypes = Arrays.asList(concat(NOTIFICATION_GROUP, '.', getManagedProfileName()));
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public List<String> getNotificationTypes() {
        return notificationTypes;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Page<ProfileRegisterNotification> getNotifications(List<String> types, Pageable pageable) {
        return profileRepository.findPending(false, pageable);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void addMetadataOptions(Options options)
    {
        super.addMetadataOptions(options);
        options.add("areaPesquisa", areaPesquisaRepository.findAllByOrderByNomeAsc());
    }

/* --------------------------------------------------------------------------------------------- */

}
