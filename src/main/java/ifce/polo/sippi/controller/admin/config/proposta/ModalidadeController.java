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
import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.dto.PageableResponse;
import ifce.polo.sippi.model.project.ProjetoModalidade;
import ifce.polo.sippi.repository.project.ProjetoModalidadeRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/proposta/modalidade")
@PreAuthorize("hasRole('ADMIN')")
public class ModalidadeController extends AbstractController
{
    @Autowired private ProjetoModalidadeRepository modalidadeRepository;
    @Autowired private ValidationService validationService;

/* --------------------------------------------------------------------------------------------- */

    @GetMapping
    public PageableResponse getModalidades(Pageable pageable) {
        return new PageableResponse(modalidadeRepository.findBy(pageable));
    }

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(ProjetoModalidade.class));
    }

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/{id}")
    public ResponseEntity<?> getModalidade(@PathVariable Short id)
    {
        ProjetoModalidade modalidade = modalidadeRepository.findById(id).orElse(null);
        return modalidade != null ? ok(modalidade) : idNotFound(id);
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<?> createModalidade(@RequestBody ProjetoModalidade modalidade)
    {
        ObjectNode error = validationService.validate(modalidade);

        if (error != null) {
            return validationFailed(error);
        }

        modalidade.setId(null);
        modalidade = modalidadeRepository.save(modalidade);

        return created(modalidade.getId());
    }

/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateModalidade(@PathVariable Short id, @RequestBody ProjetoModalidade modalidade)
    {
        if (!modalidadeRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(modalidade);

        if (error != null) {
            return validationFailed(error);
        }

        modalidade.setId(id);
        modalidadeRepository.save(modalidade);

        return ok();
    }

/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModalidade(@PathVariable Short id)
    {
        if (!modalidadeRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        modalidadeRepository.deleteById(id);
        return ok();
    }

/* --------------------------------------------------------------------------------------------- */

}
