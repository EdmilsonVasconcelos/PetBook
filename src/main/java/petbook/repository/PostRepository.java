package petbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import petbook.model.Pet;
import petbook.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByPet(Pet pet);

}
