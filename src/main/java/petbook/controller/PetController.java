package petbook.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import petbook.dto.pet.PetDTO;
import petbook.service.PetService;

@Slf4j
@RequestMapping("/v1/pet")
@RestController
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@PostMapping
	public ResponseEntity<PetDTO> savePet(@Valid @RequestBody PetDTO request) {
		
		log.debug("PetController.savePet - Start - Request:  [{}]", request);
		
		PetDTO petSaved = petService.savePet(request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(petSaved.getId())
				.toUri();
		
		ResponseEntity<PetDTO> response = ResponseEntity.created(uri).body(petSaved);
		
		log.debug("PetController.savePet - End - Request:  [{}], Response: [{}] - ", request, response);
		
		return response;
		
	}

}
