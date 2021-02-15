package ifce.polo.sippi.controller.admin.config;

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

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.PageableResponse;
import ifce.polo.sippi.model.register.InstituicaoEnsino;
import ifce.polo.sippi.repository.config.InstituicaoEnsinoRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/instituicoes")
@PreAuthorize("hasRole('ADMIN')")
public class ListagemInstituicoesController extends AbstractController
{
	@Autowired private InstituicaoEnsinoRepository instituicaoEnsinoRepository;
	@Autowired private ValidationService validationService;

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/{uf}")
    public PageableResponse getInstituicaoByUf(@PathVariable String uf, Pageable pageable) {
    	return new PageableResponse(instituicaoEnsinoRepository.findByUf(uf, pageable));
    }
    
/* --------------------------------------------------------------------------------------------- */

    @GetMapping
    public PageableResponse getInstituicaoByNome(@RequestParam String nome, Pageable pageable) {
    	return new PageableResponse(instituicaoEnsinoRepository.findByNome(nome, pageable));
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<?> createInstituicaoEnsino(@RequestBody InstituicaoEnsino instituicao)
    {
        ObjectNode error = validationService.validate(instituicao);

        if (error != null) {
            return validationFailed(error);
        }
        
        instituicao.setId(null);
        instituicao = instituicaoEnsinoRepository.save(instituicao);

        return created(instituicao.getId());
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateinstituicaoEnsino(@PathVariable Short id, 
    												 @RequestBody InstituicaoEnsino instituicao)
    {
        if (!instituicaoEnsinoRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(instituicao);

        if (error != null) {
            return validationFailed(error);
        }

        instituicao.setId(id);
        instituicaoEnsinoRepository.save(instituicao);

        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstituicaoEnsino(@PathVariable Short id)
    {
        if (!instituicaoEnsinoRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        instituicaoEnsinoRepository.deleteById(id);
        return ok();
    }  
    
/* --------------------------------------------------------------------------------------------- */
	
}
