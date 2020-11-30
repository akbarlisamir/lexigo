package hu.elte.softech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.User;
import hu.elte.softech.entity.UserTopicsAndEntrysAndFavs;

public interface UserService {

	//GET Users
	List<User> getAllUsers();

	//GET Users with Details
	List<UserTopicsAndEntrysAndFavs> getAllUsersWD();

	//GET User By Username with Details
	UserTopicsAndEntrysAndFavs findOneUserByUsernameWD(String username);

	//GET User By UserId with Details
	UserTopicsAndEntrysAndFavs findOneUserByUserIdWD(Long userId);

	//GET User By Username
	User findOneUserByUsername(String username);

	//GET User By UserId
	User findOneUserByUserId(Long userId);

	//POST User
	ResponseEntity<Object> createUser(User nU);

	//EDIT User
	User editUser(User eU, Long id);

	//DELETE User
	ResponseEntity<Void> deleteUser(Long userId);
}
