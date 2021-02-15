package ifce.polo.sippi.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.CategoriaBolsaItemList;
import ifce.polo.sippi.model.project.CategoriaBolsa;

@Repository
public interface CategoriaBolsaRepository extends JpaRepository<CategoriaBolsa, Short> 
{

/* --------------------------------------------------------------------------------------------- */

    public Page<CategoriaBolsaItemList> findBy(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}