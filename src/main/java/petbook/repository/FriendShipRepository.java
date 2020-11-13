package petbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import petbook.model.Friendship;

public interface FriendShipRepository extends JpaRepository<Friendship, Long> {

}
