package petbook.service;

import petbook.dto.post.ListPostsResponseDTO;
import petbook.dto.post.PostRequestDTO;
import petbook.dto.post.PostResponseDTO;

public interface PostService {

	public PostResponseDTO savePost(Long idPet, PostRequestDTO request);
	
	public ListPostsResponseDTO findPostsByIdTutor(Long idPet)
	
}
