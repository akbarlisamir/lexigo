package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserController {
	
	@Autowired
	private UserRepository ur;
	
	@RequestMapping(method=RequestMethod.GET, path="/login/user/{username}")
	public User findOneUser(@PathVariable String username) {
		User user = ur.findUserByUsername(username);
		if(user == null) {
			//throw new UserNotFoundException("id-" + id);
		}
		return user;
	}
	
	

}
