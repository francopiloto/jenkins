package ifce.polo.sippi.controller.admin.config.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.dto.config.UserPassword;
import ifce.polo.sippi.repository.access.UsuarioRepository;
import ifce.polo.sippi.service.access.UsuarioService;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/alterar-senha")
@PreAuthorize("hasRole('ADMIN')")
public class AdminPasswordController extends AbstractController
{
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private ValidationService validationService;
    @Autowired private UsuarioService usuarioService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(UserPassword.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    @PutMapping
    public ResponseEntity<?> changePassword(@RequestBody UserPassword userPassword)
    {
		if(!usuarioRepository.findById((long) 1).isPresent()) {
			return notFound("Not found");
		}
        
		if (!usuarioService.verifyPassword(userPassword.getCurrentPassword()))
        {
			return badRequest("Incorrect password");
        }
		
        if (!userPassword.getNewPassword().equals(userPassword.getNewPasswordConfirm()))
        {
			return badRequest("Passwords don't match");
        }	
        
        usuarioService.changePassword(userPassword.getNewPassword());
    	return ok();
    }

/* --------------------------------------------------------------------------------------------- */
    
}
