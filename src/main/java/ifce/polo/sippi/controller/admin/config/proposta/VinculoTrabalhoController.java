package ifce.polo.sippi.controller.admin.config.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.dto.PageableResponse;
import ifce.polo.sippi.model.project.VinculoTrabalho;
import ifce.polo.sippi.repository.project.VinculoTrabalhoRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/proposta/vinculo-trabalho")
@PreAuthorize("hasRole('ADMIN')")
public class VinculoTrabalhoController extends AbstractController
{
    @Autowired private VinculoTrabalhoRepository vinculoRepository;
    @Autowired private ValidationService validationService;

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(VinculoTrabalho.class));
    }
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping
    public PageableResponse getVinculoTrabalhoTable(Pageable pageable) {
        return new PageableResponse(vinculoRepository.findBy(pageable));
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<?> createVinculo(@RequestBody VinculoTrabalho vinculoTrabalho)
    {
        ObjectNode error = validationService.validate(vinculoTrabalho);

        if (error != null) {
            return validationFailed(error);
        }

        vinculoTrabalho.setId(null);
        vinculoTrabalho = vinculoRepository.save(vinculoTrabalho);

        return created(vinculoTrabalho.getId());
    }

/* --------------------------------------------------------------------------------------------- */
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVinculo(@PathVariable Short id, @RequestBody 
    									   VinculoTrabalho vinculoTrabalho)
    {
        if (!vinculoRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(vinculoTrabalho);

        if (error != null) {
            return validationFailed(error);
        }

        vinculoTrabalho.setId(id);
        vinculoRepository.save(vinculoTrabalho);

        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVinculo(@PathVariable Short id)
    {
        if (!vinculoRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        vinculoRepository.deleteById(id);
        return ok();
    }  
    
/* --------------------------------------------------------------------------------------------- */
	
	
}
