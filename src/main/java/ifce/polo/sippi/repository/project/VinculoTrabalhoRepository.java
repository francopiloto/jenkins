package ifce.polo.sippi.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.VinculoTrabalhoItemList;
import ifce.polo.sippi.model.project.VinculoTrabalho;
import ifce.polo.sippi.repository.register.TableListRepository;

@Repository
public interface VinculoTrabalhoRepository extends TableListRepository<VinculoTrabalho>
{

/* --------------------------------------------------------------------------------------------- */
    
    public Page<VinculoTrabalhoItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
