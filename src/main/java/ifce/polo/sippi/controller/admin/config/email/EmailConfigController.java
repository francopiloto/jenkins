package ifce.polo.sippi.controller.admin.config.email;

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
import ifce.polo.sippi.model.config.EmailConfig;
import ifce.polo.sippi.repository.config.EmailConfigRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/email")
@PreAuthorize("hasRole('ADMIN')")
public class EmailConfigController extends AbstractController
{
	@Autowired private EmailConfigRepository emailRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(EmailConfig.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmailConfigById(@PathVariable Short id)
    {
		EmailConfig email = emailRepository.findById(id).orElse(null);
        return email != null ? ok(email) : notFound("Not found");
    }
	
/* --------------------------------------------------------------------------------------------- */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmailConfig(@PathVariable Short id, @RequestBody EmailConfig email) 
	{
		if(!emailRepository.findById(id).isPresent()) {
			return notFound("Not found");
		}
		
		ObjectNode error = validationService.validate(email);
		
		if(error != null) {
			return validationFailed(error);
		}
		
		email.setId((short) id);
		emailRepository.save(email);
		
		return ok();
	}
	
/* --------------------------------------------------------------------------------------------- */
		
}
