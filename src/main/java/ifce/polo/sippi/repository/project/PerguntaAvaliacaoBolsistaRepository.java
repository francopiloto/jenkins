package ifce.polo.sippi.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ifce.polo.sippi.dto.config.PerguntasBolsistaItemList;
import ifce.polo.sippi.model.project.PerguntaAvaliacaoBolsista;

public interface PerguntaAvaliacaoBolsistaRepository extends JpaRepository<PerguntaAvaliacaoBolsista, Short>
{

/* --------------------------------------------------------------------------------------------- */

    public Page<PerguntasBolsistaItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */
	
}
