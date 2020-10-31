package petbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import petbook.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
