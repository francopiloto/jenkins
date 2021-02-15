package ifce.polo.sippi.controller.admin.config.dados;

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
import ifce.polo.sippi.model.register.Campus;
import ifce.polo.sippi.repository.config.TableListDataRepository;
import ifce.polo.sippi.repository.register.CampusRepository;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1/admin/config/campus")
@PreAuthorize("hasRole('ADMIN')")
public class DadosCampusController extends AbstractController
{
	@Autowired private CampusRepository campusRepository;
	@Autowired private TableListDataRepository tableListDataRepository;
	@Autowired private ValidationService validationService;
	
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(Campus.class));
    }

/* --------------------------------------------------------------------------------------------- */
	
    @GetMapping
    public PageableResponse getCampus(Pageable pageable) {
        return new PageableResponse(tableListDataRepository.loadTableListData("campus", pageable));
    }
	
/* --------------------------------------------------------------------------------------------- */
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getCampusById(@PathVariable Short id)
    {
		Campus campus = campusRepository.findById(id).orElse(null);
        return campus != null ? ok(campus) : notFound("Not found");
    }
    
/* --------------------------------------------------------------------------------------------- */

	@PostMapping
    public ResponseEntity<?> createCampus(@RequestBody Campus campus)
    {
        ObjectNode error = validationService.validate(campus);

        if (error != null) {
            return validationFailed(error);
        }
        
        campus.setId(null);
        campus = campusRepository.save(campus);

        return created(campus.getId());
    }
	
/* --------------------------------------------------------------------------------------------- */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCampus(@PathVariable Short id, @RequestBody Campus campus) 
	{
		if(!campusRepository.findById(id).isPresent()) {
			return notFound("Not found");
		}
		
		ObjectNode error = validationService.validate(campus);
		
		if(error != null) {
			return validationFailed(error);
		}
		
		campus.setId(id);
		campusRepository.save(campus);
		
		return ok();
	}
	
/* --------------------------------------------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCampus(@PathVariable Short id)
    {
        if (!campusRepository.findById(id).isPresent()) {
            return idNotFound(id);
        }

        campusRepository.deleteById(id);
        return ok();
    }
	
/* --------------------------------------------------------------------------------------------- */

}