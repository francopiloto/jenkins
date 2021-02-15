package ifce.polo.sippi.repository.register;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.register.Titulacao;

@Repository
public interface TitulacaoRepository extends CrudRepository<Titulacao, Short>
{

/* --------------------------------------------------------------------------------------------- */

    public Iterable<Titulacao> findAllByOrderByIdAsc();

/* --------------------------------------------------------------------------------------------- */

}
