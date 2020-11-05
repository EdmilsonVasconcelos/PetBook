package petbook.service;

import petbook.dto.user.UserRequestDTO;
import petbook.dto.user.UserResponseDTO;

public interface UserService {

	public UserResponseDTO saveUser(UserRequestDTO request);

}
