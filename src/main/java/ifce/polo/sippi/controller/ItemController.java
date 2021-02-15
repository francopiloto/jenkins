package ifce.polo.sippi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.service.validation.ValidationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RestController
@RequestMapping("/v1/items")
public class ItemController extends AbstractController
{
	@Autowired private ValidationService validationService;

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/metadata")
    public FormMetadata getMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(Container.class));
    }
    
/* --------------------------------------------------------------------------------------------- */
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id)
	{
		Container container = new Container();
		
		container.setId(id);
		container.setItems(Arrays.asList(new Item[] {new Item(1, "item1"), new Item(2, "item2") }));
		
		return ok(container);
	}
	
/* --------------------------------------------------------------------------------------------- */
	
	@PutMapping("/{id}")
	public ResponseEntity<?> set(@RequestBody Container container)
	{
		for (Item item : container.getItems()) {
			System.out.println(item);
		}

		return ok();
	}
	
/* --------------------------------------------------------------------------------------------- */
	
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Item 
{
	private Integer id;
    private String nome;
}

@Getter
@Setter
class Container 
{
	private Long id;
	private List<Item> items;
}
