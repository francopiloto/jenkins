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
import ifce.polo.sippi.model.config.EditavelIfce;
import ifce.polo.sippi.repository.config.EditavelIfceRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/dados-institucionais/ifce")
@PreAuthorize("hasRole('ADMIN')")
public class DadosIfceController extends AbstractController
{
	@Autowired private EditavelIfceRepository ifceRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(EditavelIfce.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getDadosIfce(@PathVariable Short id)
    {
		EditavelIfce ifce = ifceRepository.findById(id).orElse(null);
        return ifce != null ? ok(ifce) : notFound("Not found");
    }
    
/* --------------------------------------------------------------------------------------------- */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDadosIfce(@PathVariable Short id, @RequestBody EditavelIfce dadosIfce)
	{
		if(!ifceRepository.findById(id).isPresent()) {
			return notFound("Not found");
		}
		
		ObjectNode error = validationService.validate(dadosIfce);
		
		if(error != null) {
			return validationFailed(error);
		}
		
		dadosIfce.setId(id);
		ifceRepository.save(dadosIfce);
		
		return ok();
	}
	
/* --------------------------------------------------------------------------------------------- */

}