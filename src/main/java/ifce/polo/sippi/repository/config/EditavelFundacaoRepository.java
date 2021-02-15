package ifce.polo.sippi.repository.config;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.config.EditavelFundacao;

@Repository
public interface EditavelFundacaoRepository extends CrudRepository<EditavelFundacao, Short> {}
