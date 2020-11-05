package petbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import petbook.dto.pet.PetRequestDTO;
import petbook.dto.pet.PetResponseDTO;
import petbook.dto.pet.PetsForUserDTO;
import petbook.dto.user.UserResponseDTO;
import petbook.exception.PetNotFoundException;
import petbook.exception.TutorNotFoundException;
import petbook.model.Pet;
import petbook.model.User;
import petbook.repository.PetRepository;
import petbook.repository.UserRepository;
import petbook.security.jwt.JwtService;

@Slf4j
@Service
public class PetService {
	
	private static final String USER_WITH_EMAIL_NOT_EXIST = "User with email %s not exist.";
	
	private static final String PET_WITH_ID_NOT_EXIST = "Pet with id %d not exist.";
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	public PetResponseDTO savePet(PetRequestDTO request) {
		
		log.debug("PetService.savePet - Start - Input: Request [{}]", request);
		
		Pet petToSave = mapper.map(request, Pet.class);
		
		User tutor = getUserByEmail(jwtService.getCurrentUser());
		
		petToSave.setUser(tutor);
		
		Pet petSaved = petRepository.save(petToSave);
	
		PetResponseDTO response = mapper.map(petSaved, PetResponseDTO.class);
		
		log.debug("PetService.savePet - End - Request:  [{}], Response: [{}] - ", request, response);
		
		return response;
		
	}
	
	public PetsForUserDTO getPetsForUser() {
		
		log.debug("PetService.getPetsForUser - Start");
		
		User tutor = getUserByEmail(jwtService.getCurrentUser());
		
		UserResponseDTO user = mapper.map(tutor, UserResponseDTO.class);
		
		log.debug("PetService.getPetsForUser - Input: Email [{}]", tutor.getEmail());
		
		List<Pet> pets = petRepository.findByUser(tutor);
		
		List<PetResponseDTO> petsResponseDTO = pets.stream()
							.map(pet -> mapper.map(pet, PetResponseDTO.class))
							.collect(Collectors.toList());
		
		PetsForUserDTO response = PetsForUserDTO.builder().user(user).pets(petsResponseDTO).build();
		
		log.debug("PetService.getPetsForUser - End - Input:  [{}], Response: [{}] - ", tutor.getEmail(), response);
		
		return response;
		
	}
	
	public PetResponseDTO getProfilePet(Long idPet) {
		
		log.debug("PetService.getProfilePet - Start - Input: idPet [{}]", idPet);
		
		Pet pet = findPetById(idPet);
		
		PetResponseDTO response = mapper.map(pet, PetResponseDTO.class);
		
		log.debug("PetService.getProfilePet - End - Input: idPet [{}], Reponse [{}]", idPet, response);
		
		return response;
	}
	
	private Pet findPetById(Long idPet) {
		return petRepository.findById(idPet)
				.orElseThrow(() -> new PetNotFoundException(String.format(String.format(PET_WITH_ID_NOT_EXIST, idPet))));
	}
	
	private User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
                .orElseThrow(() -> new TutorNotFoundException(String.format(USER_WITH_EMAIL_NOT_EXIST, email)));
	}
	
}
