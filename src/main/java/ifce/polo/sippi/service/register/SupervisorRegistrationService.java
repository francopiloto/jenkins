package ifce.polo.sippi.service.register;

import org.springframework.stereotype.Service;

import ifce.polo.sippi.model.register.Supervisor;
import ifce.polo.sippi.repository.register.SupervisorRepository;

@Service
public class SupervisorRegistrationService extends MembroRegistrationService<Supervisor, SupervisorRepository>{

/* --------------------------------------------------------------------------------------------- */

    public SupervisorRegistrationService() {
        super(Supervisor.class);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void addMetadataOptions(Options options) {
        super.addMetadataOptions(options);
    }

/* --------------------------------------------------------------------------------------------- */
    
}
