package ifce.polo.sippi.repository.access;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.access.Permissao;

@Repository
public interface PermissaoRepository extends CrudRepository<Permissao, Short>{}
