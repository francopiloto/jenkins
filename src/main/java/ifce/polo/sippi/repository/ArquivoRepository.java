package ifce.polo.sippi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long>{}
