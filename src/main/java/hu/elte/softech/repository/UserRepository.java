package hu.elte.softech.repository;

import hu.elte.softech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User findUserByUsername(String username);

	@Query("SELECT u FROM User u WHERE u = (SELECT t.user FROM Topic t WHERE t.id = ?1)")
	public User findUserByTopic(Long topicId);


}
