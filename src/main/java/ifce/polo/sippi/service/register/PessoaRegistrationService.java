package ifce.polo.sippi.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifce.polo.sippi.model.register.Pessoa;
import ifce.polo.sippi.model.register.PessoaPerfil;
import ifce.polo.sippi.repository.register.PessoaPerfilRepository;
import ifce.polo.sippi.repository.register.PessoaRepository;

@Service
public abstract class PessoaRegistrationService<P extends PessoaPerfil, R extends PessoaPerfilRepository<P>>
        extends AbstractRegistrationService<P>
{
    @Autowired protected R profileRepository;
    @Autowired private PessoaRepository pessoaRepository;

/* --------------------------------------------------------------------------------------------- */

    public PessoaRegistrationService(Class<P> profileClass) {
        super(profileClass);
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public Availability checkAvailability(String id)
    {
        if (!validationService.isValid(Pessoa.class, "cpf", id)) {
            return Availability.INVALID;
        }

        Pessoa pessoa = pessoaRepository.findByCpf(id);

        if (pessoa == null) {
            return Availability.AVAILABLE;
        }

        if (!pessoa.isAtivado() || profileRepository.countByAtivadoAndPessoa(false, pessoa) > 0) {
            return Availability.PENDING;
        }

        if (profileRepository.countByAtivadoAndPessoa(true, pessoa) >= getMaxSimilarProfilesAllowed()) {
            return Availability.REGISTERED;
        }

        return Availability.available(pessoa.getId(), pessoa.getNome());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    public void confirmRegistration(Long profileId, boolean accept)
    {
        P profile = profileRepository.findById(profileId).orElse(null);

        if (profile.isAtivado()) {
            throw new IllegalStateException("Profile already activated");
        }

        Pessoa pessoa = profile.getPessoa();

        if (accept)
        {
            if (!pessoa.isAtivado()) {
                pessoa.setAtivado(true);
                pessoaRepository.save(pessoa);
            }

            profile.setAtivado(true);
            profileRepository.save(profile);
        }
        else
        {
            profileRepository.delete(profile);

            if (!pessoa.isAtivado()) {
                pessoaRepository.delete(pessoa);
            }
        }
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected Availability checkAvailability(P profile) {
        return checkAvailability(profile.getPessoa().getCpf());
    }

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void save(P profile)
    {
        Pessoa pessoa = profile.getPessoa();

        if (profile.isAtivado() && !pessoa.isAtivado()) {
            throw new IllegalStateException();
        }

        if (pessoa.getId() == null || !pessoaRepository.findById(pessoa.getId()).isPresent()) {
            pessoaRepository.save(pessoa);
        }

        profileRepository.save(profile);
    }

/* --------------------------------------------------------------------------------------------- */

    protected int getMaxSimilarProfilesAllowed() {
        return 1;
    }

/* --------------------------------------------------------------------------------------------- */

}
