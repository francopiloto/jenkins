package ifce.polo.sippi.service.register;

import static ifce.polo.sippi.util.StringUtils.concat;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ifce.polo.sippi.dto.register.ProfileRegisterNotification;
import ifce.polo.sippi.model.register.Empresa;
import ifce.polo.sippi.repository.register.EmpresaRepository;
import ifce.polo.sippi.service.notification.NotificationProvider;

@Service
public class EmpresaRegistrationService extends AbstractRegistrationService<Empresa>
    implements NotificationProvider
{
    @Autowired private EmpresaRepository empresaRepository;

    private final List<String> notificationTypes;

/* --------------------------------------------------------------------------------------------- */

    public EmpresaRegistrationService()
    {
        super(Empresa.class);
        notificationTypes = Arrays.asList(concat(NOTIFICATION_GROUP, '.', getManagedProfileName()));
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void confirmRegistration(Long profileId, boolean accept)
    {
        Empresa empresa = empresaRepository.findById(profileId).orElse(null);

        if (accept)
        {
            empresa.setAtivado(true);
            empresaRepository.save(empresa);
        }
        else {
            empresaRepository.delete(empresa);
        }
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Availability checkAvailability(String id)
    {
        if (!validationService.isValid(Empresa.class, "cnpj", id)) {
            return Availability.INVALID;
        }

        Empresa empresa = empresaRepository.findByCnpj(id);

        if (empresa == null) {
            return Availability.AVAILABLE;
        }

        return empresa.isAtivado() ? Availability.REGISTERED : Availability.PENDING;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public List<String> getNotificationTypes() {
        return notificationTypes;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Page<ProfileRegisterNotification> getNotifications(List<String> types, Pageable pageable) {
        return empresaRepository.findPending(false, pageable);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected Availability checkAvailability(Empresa empresa) {
        return checkAvailability(empresa.getCnpj());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void save(Empresa empresa)
    {
        if (empresa.isAtivado()) {
            throw new IllegalStateException();
        }

        empresaRepository.save(empresa);
    }

/* --------------------------------------------------------------------------------------------- */

}
