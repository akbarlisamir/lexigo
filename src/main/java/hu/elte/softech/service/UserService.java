package hu.elte.softech.service;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.User;

public interface UserService {
	
	ResponseEntity<Object> newUser(User nU);

}
