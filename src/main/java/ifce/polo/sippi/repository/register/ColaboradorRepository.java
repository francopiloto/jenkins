package ifce.polo.sippi.repository.register;

import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.register.Colaborador;

@Repository
public interface ColaboradorRepository extends PessoaPerfilRepository<Colaborador>
{

/* --------------------------------------------------------------------------------------------- */

    public Long countBySiape(String siape);

/* --------------------------------------------------------------------------------------------- */

}
