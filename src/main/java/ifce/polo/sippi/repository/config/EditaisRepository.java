package ifce.polo.sippi.repository.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.EditaisItemList;
import ifce.polo.sippi.model.config.Editais;

@Repository
public interface EditaisRepository extends JpaRepository<Editais, Short>
{

/* --------------------------------------------------------------------------------------------- */

	public Page<EditaisItemList> findByPerfil(String perfil, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */
	
	public EditaisItemList findByPerfilAndIfce(String perfil, boolean ifce);

/* --------------------------------------------------------------------------------------------- */

}
