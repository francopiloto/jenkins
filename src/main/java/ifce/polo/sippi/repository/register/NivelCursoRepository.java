package ifce.polo.sippi.repository.register;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.register.NivelCurso;

@Repository
public interface NivelCursoRepository extends CrudRepository<NivelCurso, Short>
{

/* --------------------------------------------------------------------------------------------- */

    public Iterable<NivelCurso> findAllByOrderByIdAsc();

/* --------------------------------------------------------------------------------------------- */

}
