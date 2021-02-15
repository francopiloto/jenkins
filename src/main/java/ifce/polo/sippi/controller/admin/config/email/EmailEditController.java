package ifce.polo.sippi.controller.admin.config.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import ifce.polo.sippi.dto.PageableResponse;
import ifce.polo.sippi.model.config.EmailEdit;
import ifce.polo.sippi.repository.config.EmailEditRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/email/mensagens")
@PreAuthorize("hasRole('ADMIN')")
public class EmailEditController extends AbstractController
{
	@Autowired private EmailEditRepository emailEditRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(EmailEdit.class));
    }

/* --------------------------------------------------------------------------------------------- */
    
    @GetMapping
    public PageableResponse getMensagens(Pageable pageable) {
    	return new PageableResponse(emailEditRepository.findBy(pageable));
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getMessage(@PathVariable Short id) 
    {
    	EmailEdit message = emailEditRepository.findById(id).orElse(null);
    	return message != null ? ok(message) : notFound("Not found");
    }

/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Short id, @RequestBody EmailEdit mensagem) 
    {
    	if(!emailEditRepository.findById(id).isPresent()) {
    		return notFound("Not found");
    	}
    	
    	ObjectNode error = validationService.validate(mensagem);
    	
    	if(error != null) {
    		return validationFailed(error);
    	}
    	
    	mensagem.setId(id);
    	emailEditRepository.save(mensagem);
    	
    	return ok(mensagem);
    }

/* --------------------------------------------------------------------------------------------- */
    
}	
