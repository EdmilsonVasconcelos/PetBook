package petbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import petbook.model.Pet;
import petbook.model.User;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	List<Pet> findByUser(User user);

}
