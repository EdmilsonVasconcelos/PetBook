package petbook.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import petbook.dto.post.ListPostsResponseDTO;
import petbook.dto.post.PostRequestDTO;
import petbook.dto.post.PostResponseDTO;
import petbook.exception.NotAllowedException;
import petbook.exception.TutorNotFoundException;
import petbook.model.Pet;
import petbook.model.Post;
import petbook.model.User;
import petbook.repository.PetRepository;
import petbook.repository.PostRepository;
import petbook.repository.UserRepository;
import petbook.security.jwt.JwtService;
import petbook.service.PostService;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

	private static final String PET_WITH_EMAIL_NOT_EXIST = "Pet with id %d not exist.";

	private static final String USER_WITH_EMAIL_NOT_EXIST = "User with email %s not exist.";

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Override
	public PostResponseDTO savePost(Long idPet, PostRequestDTO request) {

		log.debug("PostService.savePost - Start - Input: Request [{}]", request);

		checkPetForUser(idPet);

		Post postToSave = mapper.map(request, Post.class);

		Pet petFound = getPetById(idPet);

		postToSave.setPet(petFound);

		Post postoSaved = postRepository.save(postToSave);

		PostResponseDTO response = mapper.map(postoSaved, PostResponseDTO.class);

		log.debug("PostService.savePost - End - Input: Request: [{}], Response: [{}]", request, response);

		return response;

	}

	@Override
	public ListPostsResponseDTO findPostsByIdTutor(Long idPet) {

		log.debug("PostService.findPostsByIdTutor - Start - Input: idPet [{}]", idPet);

		checkPetForUser(idPet);

		Pet petFound = getPetById(idPet);

		List<Post> posts = postRepository.findByPet(petFound);

		ListPostsResponseDTO response = ListPostsResponseDTO.builder().idPet(idPet).posts(posts).build();

		log.debug("PostService.findPostsByIdTutor - End - Input: idPet [{}], Response [{}]", idPet, response);

		return response;

	}

	private void checkPetForUser(Long idPet) {
		User tutor = getUserByEmail(jwtService.getCurrentUser());
		Pet pet = getPetById(idPet);

		if (!pet.getId().equals(tutor.getId())) {
			throw new NotAllowedException();
		}
	}

	private User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new TutorNotFoundException(String.format(USER_WITH_EMAIL_NOT_EXIST, email)));
	}

	private Pet getPetById(Long id) {
		return petRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(String.format(PET_WITH_EMAIL_NOT_EXIST, id)));
	}

}
