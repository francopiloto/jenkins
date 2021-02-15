package ifce.polo.sippi.repository.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.SubAreaItemList;
import ifce.polo.sippi.model.config.SubArea;
import ifce.polo.sippi.repository.register.TableListRepository;

@Repository
public interface SubAreaRepository extends TableListRepository<SubArea>
{
	
/* --------------------------------------------------------------------------------------------- */

    public Page<SubAreaItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */
    
}
