package ifce.polo.sippi.controller.admin.config.dados;

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
import ifce.polo.sippi.model.config.EditavelPolo;
import ifce.polo.sippi.repository.config.EditavelPoloRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/dados-institucionais/polo")
@PreAuthorize("hasRole('ADMIN')")
public class DadosPoloController extends AbstractController
{
	@Autowired private EditavelPoloRepository poloRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(EditavelPolo.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getDadosPolo(@PathVariable Short id)
    {
		EditavelPolo polo = poloRepository.findById(id).orElse(null);
        return polo != null ? ok(polo) : notFound("Not found");
    }
    
/* --------------------------------------------------------------------------------------------- */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDadosPolo(@PathVariable Short id, @RequestBody EditavelPolo dadosPolo) 
	{
		if(!poloRepository.findById(id).isPresent()) {
			return notFound("Not found");
		}
		
		ObjectNode error = validationService.validate(dadosPolo);
		
		if(error != null) {
			return validationFailed(error);
		}
		
		dadosPolo.setId(id);
		poloRepository.save(dadosPolo);
		
		return ok();
	}
	
/* --------------------------------------------------------------------------------------------- */

}