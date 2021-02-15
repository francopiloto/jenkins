package ifce.polo.sippi.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifce.polo.sippi.model.register.Colaborador;
import ifce.polo.sippi.repository.register.CampusRepository;
import ifce.polo.sippi.repository.register.ColaboradorRepository;
import ifce.polo.sippi.repository.register.TitulacaoRepository;
import ifce.polo.sippi.service.validation.ValidationService.Context;

@Service
public class ColaboradorRegistrationService extends MembroRegistrationService<Colaborador, ColaboradorRepository>
{
    @Autowired private TitulacaoRepository titulacaoRepository;
    @Autowired private CampusRepository campusRepository;

/* --------------------------------------------------------------------------------------------- */

    public ColaboradorRegistrationService() {
        super(Colaborador.class);
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

        if (!validationService.isValid(Colaborador.class, "siape", value)) {
            return AsyncValidationResult.INVALID;
        }

        return profileRepository.countBySiape(value) > 0
                ? AsyncValidationResult.TAKEN
                : AsyncValidationResult.AVAILABLE;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void preSaveCustomValidation(Colaborador colaborador, Context context)
    {
        super.preSaveCustomValidation(colaborador, context);

        if (profileRepository.countBySiape(colaborador.getSiape()) > 0) {
            context.addError("colaborador", "siape", colaborador.getSiape(), "unique");
        }
    }

/* --------------------------------------------------------------------------------------------- */

}
