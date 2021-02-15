package ifce.polo.sippi.service;

import static ifce.polo.sippi.service.ContextProvider.getBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ifce.polo.sippi.model.register.Supervisor;
import ifce.polo.sippi.repository.register.SupervisorRepository;
import ifce.polo.sippi.service.notification.Notification;
import ifce.polo.sippi.service.notification.NotificationProvider;

@Service
public class SupervisorService
{
    @Autowired private SupervisorRepository supervisorRepository;

/* --------------------------------------------------------------------------------------------- */

    public Collection<String> getTiposNotificacao(Long supervisorId) {
        return getTiposNotificacao(supervisorId, null);
    }

/* --------------------------------------------------------------------------------------------- */

    public Collection<String> getTiposNotificacao(Long supervisorId, String pattern)
    {
        Supervisor supervisor = supervisorRepository.findById(supervisorId).orElse(null);

        Collection<String> types = supervisor.getTiposNotificacao();
        List<String> filteredTypes = new ArrayList<>();

        for (String type : types)
        {
            if (pattern == null || type.matches(pattern)) {
                filteredTypes.add(type);
            }
        }

        return filteredTypes;
    }

/* --------------------------------------------------------------------------------------------- */

    public Page<? extends Notification> getNotificacoes(Long supervisorId, String tipoNotificacao, Pageable pageable)
    {
        Supervisor supervisor = supervisorRepository.findById(supervisorId).orElse(null);
        Collection<String> types = supervisor.getTiposNotificacao();

        if (types.contains(tipoNotificacao))
        {
            for (NotificationProvider provider : getBeans(NotificationProvider.class))
            {
                if (provider.getNotificationTypes().contains(tipoNotificacao)) {
                    return provider.getNotifications(Arrays.asList(tipoNotificacao), pageable);
                }
            }
        }

        return Page.empty(pageable);
    }

/* --------------------------------------------------------------------------------------------- */

}
