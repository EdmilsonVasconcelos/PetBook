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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import petbook.dto.UserDTO;
import petbook.service.UserService;

@Slf4j
@RequestMapping("/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO request) {
		
		log.debug("UserController.saveUser - Start - Request:  [{}]", request);
		
		UserDTO userSaved = userService.saveUser(request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId())
				.toUri();
		
		ResponseEntity<UserDTO> response = ResponseEntity.created(uri).body(userSaved);
		
		log.debug("UserController.saveUser - End - Request:  [{}], Response: [{}] - ", request, response);
		
		return response;
		
	}

}
