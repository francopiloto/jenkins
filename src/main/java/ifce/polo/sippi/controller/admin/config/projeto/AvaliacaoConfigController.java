package ifce.polo.sippi.controller.admin.config.projeto;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.PageableResponse;
import ifce.polo.sippi.model.project.PerguntaAvaliacaoBolsista;
import ifce.polo.sippi.model.project.PerguntaAvaliacaoCoordenador;
import ifce.polo.sippi.repository.project.PerguntaAvaliacaoBolsistaRepository;
import ifce.polo.sippi.repository.project.PerguntaAvaliacaoCoordenadorRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/projeto/avaliacao")
@PreAuthorize("hasRole('ADMIN')")
public class AvaliacaoConfigController extends AbstractController 
{
	@Autowired private PerguntaAvaliacaoBolsistaRepository perguntaBolsistaRepository;
	@Autowired private PerguntaAvaliacaoCoordenadorRepository perguntaCoordenadorRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping("/bolsista")
    public PageableResponse getPerguntasBolsista(Pageable pageable) {
        return new PageableResponse(perguntaBolsistaRepository.findBy(pageable));
    }
    
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/bolsista/{id}")
    public ResponseEntity<?> getPerguntaBolsista(@PathVariable Short id)
    {
        PerguntaAvaliacaoBolsista pergunta = perguntaBolsistaRepository.findById(id).orElse(null);
        return pergunta != null ? ok(pergunta) : idNotFound(id);
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PostMapping("/bolsista")
    public ResponseEntity<?> createPerguntaBolsista(@RequestBody PerguntaAvaliacaoBolsista pergunta)
    {
        ObjectNode error = validationService.validate(pergunta);

        if (error != null) {
            return validationFailed(error);
        }
        
        pergunta.setId(null);
        pergunta = perguntaBolsistaRepository.save(pergunta);

        return created(pergunta.getId());
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/bolsista/{id}")
    public ResponseEntity<?> updatePerguntaBolsista(@PathVariable Short id, 
    												@RequestBody PerguntaAvaliacaoBolsista pergunta)
    {
        if (!perguntaBolsistaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(pergunta);

        if (error != null) {
            return validationFailed(error);
        }

        pergunta.setId(id);
        perguntaBolsistaRepository.save(pergunta);

        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/bolsista/{id}")
    public ResponseEntity<?> deletePerguntaBolsista(@PathVariable Short id)
    {
        if (!perguntaBolsistaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        perguntaBolsistaRepository.deleteById(id);
        return ok();
    }

/* --------------------------------------------------------------------------------------------- */
    
    @GetMapping("/coordenador")
    public PageableResponse getPerguntasCoordenador(Pageable pageable) {
        return new PageableResponse(perguntaCoordenadorRepository.findBy(pageable));
    }
    
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/coordenador/{id}")
    public ResponseEntity<?> getPerguntaCoordenador(@PathVariable Short id)
    {
        PerguntaAvaliacaoCoordenador pergunta = perguntaCoordenadorRepository.findById(id).orElse(null);
        return pergunta != null ? ok(pergunta) : idNotFound(id);
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PostMapping("/coordenador")
    public ResponseEntity<?> createPerguntaCoordenador(@RequestBody PerguntaAvaliacaoCoordenador pergunta)
    {
        ObjectNode error = validationService.validate(pergunta);

        if (error != null) {
            return validationFailed(error);
        }
        
        pergunta.setId(null);
        pergunta = perguntaCoordenadorRepository.save(pergunta);

        return created(pergunta.getId());
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/coordenador/{id}")
    public ResponseEntity<?> updatePerguntaCoordenador(@PathVariable Short id, 
    												   @RequestBody PerguntaAvaliacaoCoordenador pergunta)
    {
        if (!perguntaCoordenadorRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(pergunta);

        if (error != null) {
            return validationFailed(error);
        }

        pergunta.setId(id);
        perguntaCoordenadorRepository.save(pergunta);

        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/coordenador/{id}")
    public ResponseEntity<?> deletePerguntaCoordenador(@PathVariable Short id)
    {
        if (!perguntaCoordenadorRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        perguntaCoordenadorRepository.deleteById(id);
        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

}