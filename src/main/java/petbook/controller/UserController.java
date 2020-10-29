package petbook.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import petbook.dto.credential.CredentialsDTO;
import petbook.dto.token.TokenDTO;
import petbook.dto.user.UserDTO;
import petbook.exception.InvalidPasswordException;
import petbook.model.User;
import petbook.security.jwt.JwtService;
import petbook.service.UserService;
import petbook.service.impl.UserServiceImpl;

@Slf4j
@RequestMapping("/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userServiceImpl;
	
	private final JwtService jwtService;
	
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
	
	@PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredentialsDTO credenciais){
        try{
        	
            User participant = User.builder()
                    .email(credenciais.getLogin())
                    .password(credenciais.getPassword())
                    .build();
            
            UserDetails usuarioAutenticado = userServiceImpl.autenticar(participant);
            
            String token = jwtService.gerarToken(participant);
            
            return new TokenDTO(participant.getEmail(), token);
            
        } catch (UsernameNotFoundException | InvalidPasswordException e){
        	
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            
        }
    }

}
