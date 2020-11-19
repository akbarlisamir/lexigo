package hu.elte.softech.repository;

import hu.elte.softech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User findUserByUsername(String username);
	
	
}
