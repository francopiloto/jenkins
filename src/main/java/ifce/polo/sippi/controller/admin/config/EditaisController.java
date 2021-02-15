package ifce.polo.sippi.controller.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.dto.PageableResponse;
import ifce.polo.sippi.model.config.Editais;
import ifce.polo.sippi.repository.config.EditaisRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/editais")
@PreAuthorize("hasRole('ADMIN')")
public class EditaisController extends AbstractController
{
	@Autowired private ValidationService validationService;
	@Autowired private EditaisRepository editaisRepository;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(Editais.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping("/{perfil}")
    public PageableResponse getEditalByPerfil(@PathVariable String perfil, Pageable pageable) {
    	return new PageableResponse(editaisRepository.findByPerfil(perfil, pageable));
    }
	
/* --------------------------------------------------------------------------------------------- */

	@PostMapping
    public ResponseEntity<?> createEdital(Editais edital)
    {
        ObjectNode error = validationService.validate(edital);

        if (error != null) {
            return validationFailed(error);
        }
        
        edital.setId(null);
        edital = editaisRepository.save(edital);

        return created(edital.getId());
    }
	
/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEdital(@PathVariable Short id, Editais edital)
    {
        if (!editaisRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(edital);

        if (error != null) {
            return validationFailed(error);
        }

        edital.setId(id);
        editaisRepository.save(edital);

        return ok();
    }
	
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEdital(@PathVariable Short id)
    {
        if (!editaisRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        editaisRepository.deleteById(id);
        return ok();
    }  
    
/* --------------------------------------------------------------------------------------------- */

}
