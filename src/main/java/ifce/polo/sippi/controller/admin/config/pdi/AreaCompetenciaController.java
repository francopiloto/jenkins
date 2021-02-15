package ifce.polo.sippi.controller.admin.config.pdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.model.config.AreaCompetencia;
import ifce.polo.sippi.repository.config.AreaCompetenciaRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/areas-competencia")
@PreAuthorize("hasRole('ADMIN')")
public class AreaCompetenciaController extends AbstractController 
{
	@Autowired private AreaCompetenciaRepository areasCompetenciaRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(AreaCompetencia.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping("/{id}")
    public ResponseEntity<?> getAreaCompetencia(@PathVariable Short id)
    {
    	AreaCompetencia areaCompetencia = areasCompetenciaRepository.findById(id).orElse(null);
        return areaCompetencia != null ? ok(areaCompetencia) : notFound("Not found");
    }
    
/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping("/sub-areas")
    public ResponseEntity<?> getSubAreas()
    {
    	AreaCompetencia areaCompetencia = areasCompetenciaRepository.findById((short) 1).orElse(null);
    	long numSubAreas = areaCompetencia.getSubAreas().size();
        return areaCompetencia.getSubAreas() != null ? ok(numSubAreas) : notFound("Not found");
    }
   
/* --------------------------------------------------------------------------------------------- */

	@PutMapping("/{id}")
    public ResponseEntity<?> updateAreaCompetencia(@PathVariable Short id, @RequestBody AreaCompetencia area)
    {
        if (!areasCompetenciaRepository.findById(id).isPresent()) {
            return notFound("Not found");
        }

        ObjectNode error = validationService.validate(area);

        if (error != null) {
            return validationFailed(error);
        }

        area.setId(id);
        areasCompetenciaRepository.save(area);

        return ok();
    }  

/* --------------------------------------------------------------------------------------------- */

}
