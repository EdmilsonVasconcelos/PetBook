package petbook.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import petbook.dto.pet.PetDTO;
import petbook.exception.TutorNotFoundException;
import petbook.model.Pet;
import petbook.model.User;
import petbook.repository.PetRepository;
import petbook.repository.UserRepository;

@Slf4j
@Service
public class PetService {
	
	private static final String USER_WITH_EMAIL_NOT_EXIST = "User with email %s not exist.";
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public PetDTO savePet(PetDTO request) {
		
		log.debug("PetService.savePet - Start - Input: Request [{}]", request);
		
		Pet petToSave = mapper.map(request, Pet.class);
		
		User tutor = getUserByEmail(request.getEmailTutor());
		
		petToSave.setUser(tutor);
		
		Pet petSaved = petRepository.save(petToSave);
	
		PetDTO response = mapper.map(petSaved, PetDTO.class);
		
		response.setEmailTutor(tutor.getEmail());
		
		log.debug("PetService.savePet - End - Request:  [{}], Response: [{}] - ", request, response);
		
		return response;
		
	}
	
	private User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
                .orElseThrow(() -> new TutorNotFoundException(String.format(USER_WITH_EMAIL_NOT_EXIST, email)));
	}
	
}
