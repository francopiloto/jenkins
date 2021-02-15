package ifce.polo.sippi.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.model.register.InstituicaoEnsino;
import ifce.polo.sippi.repository.config.InstituicaoEnsinoRepository;

@RestController
@RequestMapping("/v1/cadastro/instituicoes")
public class InstituicaoRegistrationController extends AbstractController
{
    @Autowired private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

/* --------------------------------------------------------------------------------------------- */

	@GetMapping
	public Iterable<InstituicaoEnsino> getInstituicaoByNome(@RequestParam String nome) {
    	return instituicaoEnsinoRepository.findByNome(nome);
	}
	
/* --------------------------------------------------------------------------------------------- */
	
	@GetMapping("/{id}")
	public InstituicaoEnsino getInstituicaoById(@PathVariable Short id) {
		return instituicaoEnsinoRepository.findById(id).orElse(null);
	}
	
/* --------------------------------------------------------------------------------------------- */

}
