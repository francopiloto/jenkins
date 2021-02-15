package ifce.polo.sippi.repository.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.config.AreaCompetencia;

@Repository
public interface AreaCompetenciaRepository extends JpaRepository<AreaCompetencia, Short> {}
