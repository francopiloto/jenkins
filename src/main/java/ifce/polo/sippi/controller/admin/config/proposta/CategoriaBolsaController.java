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
import ifce.polo.sippi.model.project.CategoriaBolsa;
import ifce.polo.sippi.repository.project.CategoriaBolsaRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/proposta/categoria-bolsa")
@PreAuthorize("hasRole('ADMIN')")
public class CategoriaBolsaController extends AbstractController
{
    @Autowired private CategoriaBolsaRepository categoriaBolsaRepository;
    @Autowired private ValidationService validationService;

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(CategoriaBolsa.class));
    }

/* --------------------------------------------------------------------------------------------- */

    @GetMapping
    public PageableResponse getCategoriaBolsaTable(Pageable pageable) {
        return new PageableResponse(categoriaBolsaRepository.findBy(pageable));
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<?> createCategoriaBolsa(@RequestBody CategoriaBolsa categoriaBolsa)
    {
        ObjectNode error = validationService.validate(categoriaBolsa);

        if (error != null) {
            return validationFailed(error);
        }

        categoriaBolsa.setId(null);
        categoriaBolsa = categoriaBolsaRepository.save(categoriaBolsa);

        return created(categoriaBolsa.getId());
    }

/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoriaBolsa(@PathVariable Short id, 
    											  @RequestBody CategoriaBolsa categoriaBolsa)
    {
        if (!categoriaBolsaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(categoriaBolsa);

        if (error != null) {
            return validationFailed(error);
        }

        categoriaBolsa.setId(id);
        categoriaBolsaRepository.save(categoriaBolsa);

        return ok();
    }

/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoriaBolsa(@PathVariable Short id)
    {
        if (!categoriaBolsaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        categoriaBolsaRepository.deleteById(id);
        return ok();
    }

/* --------------------------------------------------------------------------------------------- */

}
