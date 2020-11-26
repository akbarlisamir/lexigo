package hu.elte.softech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.User;

public interface UserService {
	
	ResponseEntity<Object> newUser(User nU);
	
	User editUser(User eU, Long id);
	
	ResponseEntity<Void> deleteUser(Long userId);
	
	User findOneUser(String username);
	
	List<User> allUsers();
	
	ResponseEntity<?> deleteRankByUserEntry(Long userId, Long entryId);

}
