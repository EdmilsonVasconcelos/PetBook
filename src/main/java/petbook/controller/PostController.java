package petbook.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import petbook.dto.post.ListPostsResponseDTO;
import petbook.dto.post.PostRequestDTO;
import petbook.dto.post.PostResponseDTO;
import petbook.service.PostService;

@Slf4j
@RequestMapping("/v1")
@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping(value = "/post/{idPet}")
	public ResponseEntity<PostResponseDTO> savePost(@Valid @RequestBody PostRequestDTO request,
													@PathVariable("idPet") Long idPet) {
		
		log.debug("PostController.savePost - Start - Request:  [{}], idPet: [{}]", request, idPet);
		
		PostResponseDTO postSaved = postService.savePost(idPet, request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postSaved.getId())
				.toUri();
		
		ResponseEntity<PostResponseDTO> response = ResponseEntity.created(uri).body(postSaved);
		
		log.debug("PostController.savePost - End - Request:  [{}], Response: [{}] - ", request, response);
		
		return response;
		
	}
	
	@GetMapping(value = "/post/{idPet}")
	public ResponseEntity<ListPostsResponseDTO> getPostsForUser(@PathVariable("idPet") Long idPet) {
		
		log.debug("getPetsForUser.getPostsForUser - Start - idPet: [{}]", idPet);
		
		ListPostsResponseDTO response = postService.findPostsByIdTutor(idPet);
		
		log.debug("getPetsForUser.getPostsForUser - End - idPet: [{}], Response: [{}]", idPet, response);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}

}
