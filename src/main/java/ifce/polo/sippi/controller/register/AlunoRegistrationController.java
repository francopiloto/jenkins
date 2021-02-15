package ifce.polo.sippi.controller.register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.dto.register.AvailabilityRequest;
import ifce.polo.sippi.model.register.Aluno;
import ifce.polo.sippi.service.register.AlunoRegistrationService;

@RestController
@RequestMapping("/v1/cadastro/aluno")
public class AlunoRegistrationController
    extends AbstractRegistrationController<Aluno, AlunoRegistrationService>
{

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public ResponseEntity<?> getMetadata() {
        return super.getMetadata();
    }

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/validate")
    public ResponseEntity<?> validateAsync(String property, String value) {
        return super.validateAsync(property, value);
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping("/availability")
    public ResponseEntity<?> checkAvailability(@RequestBody AvailabilityRequest request) {
        return super.checkAvailability(request.getId());
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<?> doRegistration(Aluno aluno) {
        return super.doRegistration(aluno);
    }

/* --------------------------------------------------------------------------------------------- */

}
