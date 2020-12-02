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

	//GET Users With Details
	@RequestMapping(method=RequestMethod.GET,path="/users/get")
	public List<UserTopicsAndEntrysAndFavs> getAllWD() {
	    return us.getAllUsersWD();
	}

	//GET Users
	@RequestMapping(method=RequestMethod.GET,path="/users")
	public List<User> getAll() {
	    return us.getAllUsers();
	}

//	//GET User By Username With Details
//	@RequestMapping(method=RequestMethod.GET, path="/user/{username}/get")
//	public UserTopicsAndEntrysAndFavs getOneUserByUsernameWD(@PathVariable String username) {
//		return us.findOneUserByUsernameWD(username);
//	}

	//GET User By UserId With Details
	@RequestMapping(method=RequestMethod.GET, path="/user/{userId}/get")
	public UserTopicsAndEntrysAndFavs getOneUserByUserIdWD(@PathVariable Long userId) {
		return us.findOneUserByUserIdWD(userId);
	}

	//GET User By Username
	@RequestMapping(method=RequestMethod.GET, path="/login/user/{username}")
	public User getOneUserByUsername(@PathVariable String username) {
		return us.findOneUserByUsername(username);
	}

	//GET User By UserId
	@RequestMapping(method=RequestMethod.GET, path="/user/{userId}")
	public User getOneUserByUserId(@PathVariable Long userId) {
		return us.findOneUserByUserId(userId);
	}

	//POST User
	@RequestMapping(method=RequestMethod.POST, path="/register/user")
	public ResponseEntity<Object> postUser(@RequestBody User nU) {
		return us.createUser(nU);
	}

	//EDIT User
	@RequestMapping(method=RequestMethod.PUT,path="/user/edit/{id}")
	public User putUser(@RequestBody User eU, @PathVariable Long id) {
	    return us.editUser(eU, id);
	  }

	//DELETE User
	@RequestMapping(method=RequestMethod.DELETE,path="/user/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
	    return us.deleteUser(userId);
	}

}
