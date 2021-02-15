package ifce.polo.sippi.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ifce.polo.sippi.dto.config.PerguntasCoordenadorItemList;
import ifce.polo.sippi.model.project.PerguntaAvaliacaoCoordenador;

public interface PerguntaAvaliacaoCoordenadorRepository extends JpaRepository<PerguntaAvaliacaoCoordenador, Short>
{
	
/* --------------------------------------------------------------------------------------------- */

    public Page<PerguntasCoordenadorItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */
	
}
