package ifce.polo.sippi.repository.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.config.InstituicaoEnsinoItemList;
import ifce.polo.sippi.model.register.InstituicaoEnsino;

@Repository
public interface InstituicaoEnsinoRepository extends JpaRepository<InstituicaoEnsino, Short>
{
    
/* --------------------------------------------------------------------------------------------- */
    
	@Query(value = "SELECT * FROM instituicao_ensino WHERE LOWER(nome) LIKE "
			+ "LOWER(CONCAT('%', ?1, '%')) OR LOWER(sigla) LIKE LOWER(CONCAT(?1, '%'))", 
		   nativeQuery = true)
    public Iterable<InstituicaoEnsino> findByNome(String nome);
    
/* --------------------------------------------------------------------------------------------- */

    public Page<InstituicaoEnsinoItemList> findByUf(String uf, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */
    
    @Query(value = "SELECT * FROM instituicao_ensino WHERE LOWER(nome) LIKE "
			+ "LOWER(CONCAT('%', ?1, '%')) OR LOWER(sigla) LIKE LOWER(CONCAT(?1, '%'))", 
		   nativeQuery = true)
    public Page<InstituicaoEnsinoItemList> findByNome(String nome, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */
  
}
