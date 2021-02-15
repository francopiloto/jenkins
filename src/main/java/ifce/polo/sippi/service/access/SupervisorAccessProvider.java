package ifce.polo.sippi.service.access;

import org.springframework.stereotype.Component;

import ifce.polo.sippi.model.register.Supervisor;
import ifce.polo.sippi.repository.register.SupervisorRepository;

@Component
public class SupervisorAccessProvider extends ProfileAccessProvider<Supervisor, SupervisorRepository>
{

/* --------------------------------------------------------------------------------------------- */

    public SupervisorAccessProvider() {
        super(Supervisor.class, null);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public int getPriority() {
        return 1;
    }

/* --------------------------------------------------------------------------------------------- */

}
