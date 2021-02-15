package ifce.polo.sippi.repository.config;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.config.EditavelPolo;

@Repository
public interface EditavelPoloRepository extends CrudRepository<EditavelPolo, Short> {}
