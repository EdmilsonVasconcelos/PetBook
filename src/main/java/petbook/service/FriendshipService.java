package petbook.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import petbook.dto.friendship.FriendshipDTO;
import petbook.exception.NotAllowedException;
import petbook.exception.TutorNotFoundException;
import petbook.model.Friendship;
import petbook.model.User;
import petbook.repository.FriendShipRepository;
import petbook.repository.UserRepository;
import petbook.security.jwt.JwtService;

@Slf4j
@Service
public class FriendshipService {
	
	private static final String USER_WITH_EMAIL_NOT_EXIST = "User with email %s not exist.";
	
	@Autowired
	private FriendShipRepository friendShipRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtService jwtService;
	
	public FriendshipDTO updateStatusFriendship(FriendshipDTO request) {
		
		log.debug("FriendshipService.createFriendship - Start - Input: Request [{}]", request);
		
		Friendship friendshipToSave = mapper.map(request, Friendship.class);
		
		checkTokenForRequest(request.getIdRequester());
		
		Friendship friendshipSaved = friendShipRepository.save(friendshipToSave);
		
		FriendshipDTO response = mapper.map(friendshipSaved, FriendshipDTO.class);
		
		log.debug("FriendshipService.createFriendship - End - Input: Request [{}], Reponse [{}]", request, response);
		
		return response;
		
	}
	
	private void checkTokenForRequest(Long id) {
		User tutor = getUserByEmail(jwtService.getCurrentUser());
		if(!id.equals(tutor.getId())) {
			throw new NotAllowedException();
		}
	}
	
	private User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
                .orElseThrow(() -> new TutorNotFoundException(String.format(USER_WITH_EMAIL_NOT_EXIST, email)));
	}
}
 