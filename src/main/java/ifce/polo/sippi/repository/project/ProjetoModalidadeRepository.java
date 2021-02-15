package ifce.polo.sippi.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.ProjetoModalidadeItemList;
import ifce.polo.sippi.model.project.ProjetoModalidade;

@Repository
public interface ProjetoModalidadeRepository extends JpaRepository<ProjetoModalidade, Short>
{

/* --------------------------------------------------------------------------------------------- */

    public Page<ProjetoModalidadeItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
