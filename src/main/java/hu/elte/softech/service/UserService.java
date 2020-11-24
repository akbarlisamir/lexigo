package hu.elte.softech.service;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.User;

public interface UserService {
	
	ResponseEntity<Object> newUser(User nU);
	
	User editUser(User eU, Long id);
	
	ResponseEntity<Void> deleteUser(Long id);
	
	User findOneUser(String username);

}
