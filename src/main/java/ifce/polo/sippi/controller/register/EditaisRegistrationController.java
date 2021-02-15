package ifce.polo.sippi.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.config.EditaisItemList;
import ifce.polo.sippi.repository.config.EditaisRepository;

@RestController
@RequestMapping("/v1/cadastro/editais")
public class EditaisRegistrationController extends AbstractController
{
	@Autowired private EditaisRepository editaisRepository;

/* --------------------------------------------------------------------------------------------- */

	@GetMapping
	public ResponseEntity<?> getEditalByPerfilAndIfce(@RequestParam String perfil, @RequestParam boolean ifce, Pageable pageable) {
		EditaisItemList editais = editaisRepository.findByPerfilAndIfce(perfil, ifce);
        return editais != null ? ok(editais) : notFound(perfil);
	}
	
/* --------------------------------------------------------------------------------------------- */

}
