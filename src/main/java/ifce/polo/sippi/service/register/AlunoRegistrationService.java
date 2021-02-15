package ifce.polo.sippi.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifce.polo.sippi.model.register.Aluno;
import ifce.polo.sippi.repository.register.AlunoRepository;
import ifce.polo.sippi.repository.register.NivelCursoRepository;
import ifce.polo.sippi.service.validation.ValidationService.Context;

@Service
public class AlunoRegistrationService extends MembroRegistrationService<Aluno, AlunoRepository>
{
    @Autowired private NivelCursoRepository nivelCursoRepository;

/* --------------------------------------------------------------------------------------------- */

    public AlunoRegistrationService() {
        super(Aluno.class);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void addMetadataOptions(Options options)
    {
        super.addMetadataOptions(options);
        options.add("nivelCurso", nivelCursoRepository.findAllByOrderByIdAsc());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public AsyncValidationResult validateAsync(String property, String value)
    {
        if (!property.contentEquals("matricula")) {
            return AsyncValidationResult.UNKNOWN;
        }

        if (!validationService.isValid(Aluno.class, "matricula", value)) {
            return AsyncValidationResult.INVALID;
        }

        return profileRepository.countByMatricula(value) > 0
                ? AsyncValidationResult.TAKEN
                : AsyncValidationResult.AVAILABLE;
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void preSaveCustomValidation(Aluno aluno, Context context)
    {
        super.preSaveCustomValidation(aluno, context);

        if (profileRepository.countByMatricula(aluno.getMatricula()) > 0) {
            context.addError("aluno", "matricula", aluno.getMatricula(), "Unique");
        }
    }

/* --------------------------------------------------------------------------------------------- */

}
