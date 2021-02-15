package ifce.polo.sippi.repository.register;

import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.register.Aluno;

@Repository
public interface AlunoRepository extends PessoaPerfilRepository<Aluno>
{

/* --------------------------------------------------------------------------------------------- */

    public Long countByMatricula(String matricula);

/* --------------------------------------------------------------------------------------------- */

}
