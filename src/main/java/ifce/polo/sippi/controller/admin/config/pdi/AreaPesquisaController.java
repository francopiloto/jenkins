package ifce.polo.sippi.controller.admin.config.pdi;

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
import ifce.polo.sippi.model.register.AreaPesquisa;
import ifce.polo.sippi.repository.config.TableListDataRepository;
import ifce.polo.sippi.repository.register.AreaPesquisaRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/areas-pesquisa")
@PreAuthorize("hasRole('ADMIN')")
public class AreaPesquisaController extends AbstractController 
{
	@Autowired private AreaPesquisaRepository areasPesquisaRepository;
	@Autowired private TableListDataRepository tableListDataRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(AreaPesquisa.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
    
    @GetMapping
    public PageableResponse getAreasPesquisa(Pageable pageable) {
    	return new PageableResponse(tableListDataRepository.loadTableListData("area_pesquisa", pageable));
    }

/* --------------------------------------------------------------------------------------------- */
    
    @PostMapping
    public ResponseEntity<?> createAreaPesquisa(@RequestBody AreaPesquisa area) 
    {
    	ObjectNode error = validationService.validate(area);

        if (error != null) {
            return validationFailed(error);
        }

        area.setId(null);
        area = areasPesquisaRepository.save(area);

        return created(area.getId());
    }
    
/* --------------------------------------------------------------------------------------------- */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAreaPesquisa(@PathVariable Short id, @RequestBody AreaPesquisa area)
    {
        if (!areasPesquisaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        ObjectNode error = validationService.validate(area);

        if (error != null) {
            return validationFailed(error);
        }

        area.setId(id);
        areasPesquisaRepository.save(area);

        return ok();
    }
    
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAreaPesquisa(@PathVariable Short id)
    {
        if (!areasPesquisaRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        areasPesquisaRepository.deleteById(id);
        return ok();
    }  
    
/* --------------------------------------------------------------------------------------------- */

}
