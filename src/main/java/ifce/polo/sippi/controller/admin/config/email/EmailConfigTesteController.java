package ifce.polo.sippi.controller.admin.config.email;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.annotation.Email;
import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.model.config.EmailConfig;
import ifce.polo.sippi.repository.config.EmailConfigRepository;
import ifce.polo.sippi.service.validation.ValidationService;
import lombok.Setter;

@RestController
@RequestMapping("/v1/admin/config/email/test-email")
@PreAuthorize("hasRole('ADMIN')")
public class EmailConfigTesteController extends AbstractController
{
	@Autowired private EmailConfigRepository emailRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(EmailTeste.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
    @PostMapping
    public ResponseEntity<?> sendEmailTest(@RequestBody EmailTeste emailTeste)
    {
		EmailConfig email = emailRepository.findById((short) 1).orElse(null);
		System.out.println(emailTeste.getEmail());
        return email != null ? ok(email) : notFound("Not found");
    }
	
/* --------------------------------------------------------------------------------------------- */
    
    @Setter
    public static class EmailTeste 
    {
    	private String email;
    	
    	@NotNull
    	@Email
    	public String getEmail() {
    		return email;
    	}

    }
    
/* --------------------------------------------------------------------------------------------- */
    
}
