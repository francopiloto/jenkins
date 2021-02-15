package ifce.polo.sippi.controller.register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.dto.register.AvailabilityRequest;
import ifce.polo.sippi.model.register.Colaborador;
import ifce.polo.sippi.service.register.ColaboradorRegistrationService;

@RestController
@RequestMapping("/v1/cadastro/colaborador")
public class ColaboradorRegistrationController
    extends AbstractRegistrationController<Colaborador, ColaboradorRegistrationService>
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
    public ResponseEntity<?> doRegistration(Colaborador colaborador) {
        return super.doRegistration(colaborador);
    }

/* --------------------------------------------------------------------------------------------- */

}
