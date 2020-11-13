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
import petbook.dto.friendship.FriendshipDTO;
import petbook.service.FriendshipService;

@Slf4j
@RequestMapping("/v1")
@RestController
public class FiendshipController {
	
	@Autowired
	private FriendshipService friendshipService;
	
	@PostMapping(value = "/friendship")
	public ResponseEntity<FriendshipDTO> updateStatusFriendship(@Valid @RequestBody FriendshipDTO request) {
		
		log.debug("FiendshipController.updateStatusFriendship - Start - Request:  [{}]", request);
		
		FriendshipDTO friendUpdated = friendshipService.updateStatusFriendship(request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(friendUpdated.getId())
				.toUri();
		
		ResponseEntity<FriendshipDTO> response = ResponseEntity.created(uri).body(friendUpdated);
		
		log.debug("FiendshipController.updateStatusFriendship - Start - Request:  [{}], Response: [{}]", request, response);
		
		return response;
		
	}

}
