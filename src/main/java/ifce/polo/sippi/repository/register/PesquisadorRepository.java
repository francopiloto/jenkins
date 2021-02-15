package ifce.polo.sippi.repository.register;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.register.Pesquisador;

@Repository
public interface PesquisadorRepository extends PessoaPerfilRepository<Pesquisador>
{

/* --------------------------------------------------------------------------------------------- */

    public Long countBySiape(String siape);

/* --------------------------------------------------------------------------------------------- */

    @Query(value = "SELECT p.id, CONCAT(p.nome, ' - ', pes.siape) as nome FROM pesquisador pes " +
                   "JOIN pessoa p on p.id = pes.pessoa_id WHERE pes.ativado = ?1 ORDER BY p.nome ASC",
           nativeQuery = true)
    public Object[] findCoordinators(boolean ativado);

/* --------------------------------------------------------------------------------------------- */

}
