package ifce.polo.sippi.controller.supervisor;

import static ifce.polo.sippi.service.ContextProvider.getCredentials;

import java.util.Collection;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.controller.register.AbstractRegistrationController;
import ifce.polo.sippi.dto.PageableResponse;
import ifce.polo.sippi.model.register.Supervisor;
import ifce.polo.sippi.repository.register.SupervisorRepository;
import ifce.polo.sippi.security.UserCredentials;
import ifce.polo.sippi.service.SupervisorService;
import ifce.polo.sippi.service.register.SupervisorRegistrationService;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/registrar-supervisor")
@PreAuthorize("hasRole('ADMIN')")
public class SupervisorController 
	extends AbstractRegistrationController<Supervisor, SupervisorRegistrationService>
{
    @Autowired private SupervisorService supervisorService;
	@Autowired private ValidationService validationService;
	@Autowired private SupervisorRegistrationService supervisorRegistrationService;
    @Autowired private SupervisorRepository supervisorRepository;

/* --------------------------------------------------------------------------------------------- */
  
    @GetMapping("/metadata")
    public ResponseEntity<?> getMetadata() {
        return super.getMetadata();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<?> doRegistration(@RequestBody Supervisor supervisor) {
        super.doRegistration(supervisor);
        supervisorRegistrationService.confirmRegistration(supervisor.getId(), true);
        return ok(supervisor);
    }

/* --------------------------------------------------------------------------------------------- */
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupervisor(@PathVariable Long id, @RequestBody 
    									   Supervisor supervisor)
    {
        if (!supervisorRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(supervisor);

        if (error != null) {
            return validationFailed(error);
        }

        supervisor.setId(id);
        supervisorRepository.save(supervisor);

        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVinculo(@PathVariable Long id)
    {
        if (!supervisorRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        supervisorRepository.deleteById(id);
        return ok();
    }  
    
/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping("/notificacoes/tipos")
    public Collection<String> getTiposNotificacao(@RequestParam(required = false) String categoria)
    {
        UserCredentials credentials = getCredentials();

        if (categoria != null) {
            categoria += "[.].+";
        }

        return supervisorService.getTiposNotificacao(credentials.getProfileId(), categoria);
    }

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/notificacoes")
    public PageableResponse getNotificacoes(@RequestParam String tipo, Pageable pageable)
    {
        UserCredentials credentials = getCredentials();
        return new PageableResponse(supervisorService.getNotificacoes(credentials.getProfileId(), tipo, pageable));
    }

/* --------------------------------------------------------------------------------------------- */

}
