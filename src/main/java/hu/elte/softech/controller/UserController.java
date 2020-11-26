package hu.elte.softech.controller;

import java.util.List;
import hu.elte.softech.entity.*;
import hu.elte.softech.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService us;
	
	@RequestMapping(method=RequestMethod.GET,path="/users")
	public List<User> all() {
	    return us.allUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/login/user/{username}")
	public User findOneUser(@PathVariable String username) {
		return us.findOneUser(username);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/register/user")
	public ResponseEntity<Object> newUser(@RequestBody User nU) {
		return us.newUser(nU);
	}
	
	@RequestMapping(method=RequestMethod.PUT,path="/user/edit/{id}")
	public User editUser(@RequestBody User eU, @PathVariable Long id) {
	    return us.editUser(eU, id);
	  }
	
	//DELETE
	@RequestMapping(method=RequestMethod.DELETE,path="/user/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
	    return us.deleteUser(userId);
	}

}
