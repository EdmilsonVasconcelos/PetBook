package petbook.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import petbook.dto.user.UserRequestDTO;
import petbook.dto.user.UserResponseDTO;
import petbook.exception.EmailExistException;
import petbook.model.User;
import petbook.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	
	private static final String USER_WITH_EMAIL_EXIST = "User with email %s exist.";
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserResponseDTO saveUser(UserRequestDTO request) {
		
		log.debug("UserService.saveUser - Start - Input: Request [{}]", request);
		
		checkExistEmail(request.getEmail());
		
		User userToSave = mapper.map(request, User.class);
		
		userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
		
		User userSaved = userRepository.save(userToSave);
		
		UserResponseDTO response = mapper.map(userSaved, UserResponseDTO.class);
		
		log.debug("UserService.saveUser - End - Request:  [{}], Response: [{}] - ", request, response);
		
		return response;
		
	}
	
	private void checkExistEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			throw new EmailExistException(String.format(USER_WITH_EMAIL_EXIST, email));
		}
	}

}
