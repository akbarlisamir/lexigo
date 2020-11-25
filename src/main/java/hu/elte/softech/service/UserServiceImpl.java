package hu.elte.softech.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.User;
import hu.elte.softech.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository ur;

	@Override
	public ResponseEntity<Object> newUser(User nU) {
		User savedUser = ur.save(nU);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public User editUser(User eU, Long id) {
		return ur.findById(id).map(user -> {
	    	  user.setUsername(eU.getUsername());
	    	  user.setEmail(eU.getEmail());
	    	  user.setPassword(eU.getPassword());
	    	  user.setRole(eU.getRole());
	    	  return ur.save(user);
	      })
	      .orElseGet(() -> {
	    	  eU.setId(id);
	    	  return ur.save(eU);
	      });
	}

	@Override
	public ResponseEntity<Void> deleteUser(Long id) {
		ur.deleteById(id);
	    
	    return ResponseEntity.noContent().build();
	}

	@Override
	public User findOneUser(String username) {
		User user = ur.findUserByUsername(username);
		if(user == null) {
			//throw new UserNotFoundException("id-" + id);
		}
		return user;
	}

	@Override
	public List<User> allUsers() {
		return ur.findAll();
	}
	
	

}
