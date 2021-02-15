package ifce.polo.sippi.controller.admin.config.proposta;

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
import ifce.polo.sippi.model.project.CaracterizacaoPerguntas;
import ifce.polo.sippi.repository.project.CaracterizacaoPerguntasRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/proposta/formulariocaraterizacao")
@PreAuthorize("hasRole('ADMIN')")
public class FormularioCaracterizacaoController extends AbstractController 
{
	@Autowired private CaracterizacaoPerguntasRepository perguntaRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping
    public PageableResponse getFormularioCaracterizacao(Pageable pageable) {
        return new PageableResponse(perguntaRepository.findBy(pageable));
    }
    
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormularioCaracterizacao(@PathVariable Short id)
    {
        CaracterizacaoPerguntas pergunta = perguntaRepository.findById(id).orElse(null);
        return pergunta != null ? ok(pergunta) : idNotFound(id);
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<?> createPergunta(@RequestBody CaracterizacaoPerguntas pergunta)
    {
        ObjectNode error = validationService.validate(pergunta);

        if (error != null) {
            return validationFailed(error);
        }
        
        pergunta.setId(null);
        pergunta = perguntaRepository.save(pergunta);

        return created(pergunta.getId());
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePergunta(@PathVariable Short id, 
    										@RequestBody CaracterizacaoPerguntas pergunta)
    {
        if (!perguntaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(pergunta);

        if (error != null) {
            return validationFailed(error);
        }

        pergunta.setId(id);
        perguntaRepository.save(pergunta);

        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePergunta(@PathVariable Short id)
    {
        if (!perguntaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        perguntaRepository.deleteById(id);
        return ok();
    }

/* --------------------------------------------------------------------------------------------- */
    
}