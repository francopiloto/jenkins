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
import ifce.polo.sippi.model.config.EditavelFundacao;
import ifce.polo.sippi.repository.config.EditavelFundacaoRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/dados-institucionais/fundacao")
@PreAuthorize("hasRole('ADMIN')")
public class DadosFundacaoController extends AbstractController
{
	@Autowired private EditavelFundacaoRepository fundacaoRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(EditavelFundacao.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getDadosFundacao(@PathVariable Short id)
    {
		EditavelFundacao fundacao = fundacaoRepository.findById(id).orElse(null);
        return fundacao != null ? ok(fundacao) : notFound("Not found");
    }
    
/* --------------------------------------------------------------------------------------------- */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDadosFundacao(@PathVariable Short id, @RequestBody EditavelFundacao dadosFundacao) 
	{
		if(!fundacaoRepository.findById(id).isPresent()) {
			return notFound("Not found");
		}
		
		ObjectNode error = validationService.validate(dadosFundacao);
		
		if(error != null) {
			return validationFailed(error);
		}
		
		dadosFundacao.setId(id);
		fundacaoRepository.save(dadosFundacao);
		
		return ok();
	}
	
/* --------------------------------------------------------------------------------------------- */
}