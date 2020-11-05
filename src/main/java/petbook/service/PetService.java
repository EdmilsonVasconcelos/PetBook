package petbook.service;

import petbook.dto.pet.PetRequestDTO;
import petbook.dto.pet.PetResponseDTO;
import petbook.dto.pet.PetsForUserDTO;

public interface PetService {

	public PetResponseDTO savePet(PetRequestDTO request);

	public PetsForUserDTO getPetsForUser();

	public PetResponseDTO getProfilePet(Long idPet);

}
