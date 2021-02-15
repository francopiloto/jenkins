package ifce.polo.sippi.service.access;

import org.springframework.stereotype.Component;

import ifce.polo.sippi.model.register.Pesquisador;
import ifce.polo.sippi.repository.register.PesquisadorRepository;

@Component
public class PesquisadorAccessProvider extends ProfileAccessProvider<Pesquisador, PesquisadorRepository>
{

/* --------------------------------------------------------------------------------------------- */

    public PesquisadorAccessProvider() {
        super(Pesquisador.class, "siape");
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public int getPriority() {
        return 2;
    }

/* --------------------------------------------------------------------------------------------- */

}
