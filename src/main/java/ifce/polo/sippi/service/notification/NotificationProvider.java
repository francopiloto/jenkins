package ifce.polo.sippi.service.notification;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationProvider
{

/* --------------------------------------------------------------------------------------------- */

    public List<String> getNotificationTypes();

/* --------------------------------------------------------------------------------------------- */

    public Page<? extends Notification> getNotifications(List<String> types, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
