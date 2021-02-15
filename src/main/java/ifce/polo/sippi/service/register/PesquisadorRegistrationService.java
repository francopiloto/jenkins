package ifce.polo.sippi.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifce.polo.sippi.model.register.Pesquisador;
import ifce.polo.sippi.repository.register.CampusRepository;
import ifce.polo.sippi.repository.register.PesquisadorRepository;
import ifce.polo.sippi.repository.register.TitulacaoRepository;
import ifce.polo.sippi.service.validation.ValidationService.Context;

@Service
public class PesquisadorRegistrationService extends MembroRegistrationService<Pesquisador, PesquisadorRepository>
{
    @Autowired private TitulacaoRepository titulacaoRepository;
    @Autowired private CampusRepository campusRepository;

/* --------------------------------------------------------------------------------------------- */

    public PesquisadorRegistrationService() {
        super(Pesquisador.class);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void addMetadataOptions(Options options)
    {
        super.addMetadataOptions(options);

        options.add("campus", campusRepository.findAllByOrderByNomeAsc());
        options.add("titulacao", titulacaoRepository.findAllByOrderByIdAsc());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public AsyncValidationResult validateAsync(String property, String value)
    {
        if (!property.contentEquals("siape")) {
            return AsyncValidationResult.UNKNOWN;
        }

        if (!validationService.isValid(Pesquisador.class, "siape", value)) {
            return AsyncValidationResult.INVALID;
        }

        return profileRepository.countBySiape(value) > 0
                ? AsyncValidationResult.TAKEN
                : AsyncValidationResult.AVAILABLE;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void preSaveCustomValidation(Pesquisador pesquisador, Context context)
    {
        super.preSaveCustomValidation(pesquisador, context);

        if (profileRepository.countBySiape(pesquisador.getSiape()) > 0) {
            context.addError("pesquisador", "siape", pesquisador.getSiape(), "unique");
        }
    }

/* --------------------------------------------------------------------------------------------- */

}
