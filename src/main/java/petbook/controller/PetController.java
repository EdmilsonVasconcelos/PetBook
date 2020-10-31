package petbook.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import petbook.dto.pet.PetRequestDTO;
import petbook.dto.pet.PetResponseDTO;
import petbook.dto.pet.PetsForUserDTO;
import petbook.service.PetService;

@Slf4j
@RequestMapping("/v1")
@RestController
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@PostMapping(value = "/pet")
	public ResponseEntity<PetResponseDTO> savePet(@Valid @RequestBody PetRequestDTO request) {
		
		log.debug("PetController.savePet - Start - Request:  [{}]", request);
		
		PetResponseDTO petSaved = petService.savePet(request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(petSaved.getId())
				.toUri();
		
		ResponseEntity<PetResponseDTO> response = ResponseEntity.created(uri).body(petSaved);
		
		log.debug("PetController.savePet - End - Request:  [{}], Response: [{}] - ", request, response);
		
		return response;
		
	}
	
	@GetMapping(value = "/pet/find-for-user")
	public ResponseEntity<PetsForUserDTO> getPetsForUser() {
		
		log.debug("PetController.getPetsForUser - Start");
		
		PetsForUserDTO response = petService.getPetsForUser();
		
		log.debug("PetController.getPetsForUser - End - Response: [{}]", response);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}

}
