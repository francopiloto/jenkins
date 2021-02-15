package ifce.polo.sippi.repository.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.EmailEditItemList;
import ifce.polo.sippi.model.config.EmailEdit;

@Repository
public interface EmailEditRepository extends JpaRepository<EmailEdit, Short> 
{

/* --------------------------------------------------------------------------------------------- */

	public Page<EmailEditItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
