package petbook.dto.pet;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import petbook.dto.user.UserResponseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetsForUserDTO {
	
	private UserResponseDTO user;
	
	private List<PetResponseDTO> pets;

}
