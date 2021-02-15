package ifce.polo.sippi.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.CaracterizacaoPerguntasItemList;
import ifce.polo.sippi.model.project.CaracterizacaoPerguntas;

@Repository
public interface CaracterizacaoPerguntasRepository extends JpaRepository<CaracterizacaoPerguntas, Short>
{

/* --------------------------------------------------------------------------------------------- */

    public Page<CaracterizacaoPerguntasItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}