package hu.elte.softech.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.User;
import hu.elte.softech.repository.EntryRepository;
import hu.elte.softech.repository.RankingRepository;
import hu.elte.softech.repository.TopicRepository;
import hu.elte.softech.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private RankingRepository rr;	

	@Autowired
	private TopicRepository tpr;

	@Autowired
	private EntryRepository er;
	
	@Autowired
	private TopicService ts;
	
	@Autowired
	private EntryService es;

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
	
	
	
	
	//DELETE
	@Override
	public ResponseEntity<?> deleteRankByUserEntry(Long userId, Long entryId) {
		rr.delFromRankingByUserEntry(userId, entryId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> deleteUser(Long userId) {
		User user = ur.findById(userId).get();
		List<Entry> listEntry = er.findAllForUser(user);
		for(Entry e: listEntry) {
			es.deleteEntry(e.getId());
		}
		rr.delFromRankingByUser(userId);
		tpr.delFromFollowTopicByUser(userId);
		er.delFromFavoriteByUser(userId);
		er.delFromEntryByUser(userId);
		List<Topic> listTopic = tpr.findAllForUser(user);
		for(Topic t: listTopic) {
			ts.deleteTopic(t.getId());
		}
		ur.deleteById(userId);
		return ResponseEntity.noContent().build();
	}
	
	
	
//	@Override
//	public ResponseEntity<Void> deleteUser(Long id) {
//		User u = ur.findById(id).get();
//		for(Topic t: u.getTopics()) {
//			ts.deleteTopic(t.getId());
//		}
//		for(Entry e: u.getEntries()) {
//			es.deleteEntry(e.getId());
//		}
//		ur.deleteById(id);
	    
//	    return ResponseEntity.noContent().build();
//	}

}
