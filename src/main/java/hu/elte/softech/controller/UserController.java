package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;
import hu.elte.softech.service.UserService;

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
	private UserService us;
	
	@Autowired
	private UserRepository ur;
	
	@RequestMapping(method=RequestMethod.GET,path="/users")
	public List<User> all() {
	    return ur.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/login/user/{username}")
	public User findOneUser(@PathVariable String username) {
		User user = ur.findUserByUsername(username);
		if(user == null) {
			//throw new UserNotFoundException("id-" + id);
		}
		return user;
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/register/user")
	public ResponseEntity<Object> newUser(@RequestBody User nU) {
		
		return us.newUser(nU);
	}
	
	@RequestMapping(method=RequestMethod.PUT,path="/user/edit/{id}")
	public User editUser(@RequestBody User nU, @PathVariable Long id) {
	    return ur.findById(id).map(user -> {
	    	  user.setUsername(nU.getUsername());
	    	  user.setEmail(nU.getEmail());
	    	  user.setPassword(nU.getPassword());
	    	  user.setRole(nU.getRole());
	    	  return ur.save(user);
	      })
	      .orElseGet(() -> {
	    	  nU.setId(id);
	    	  return ur.save(nU);
	      });
	  }
	
	@RequestMapping(method=RequestMethod.DELETE,path="/user/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
	    ur.deleteById(id);
	}

}
