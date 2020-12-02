package hu.elte.softech.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.TopicUserTagsEntrys;
import hu.elte.softech.entity.User;
import hu.elte.softech.entity.UserTopicsAndEntrysAndFavs;
import hu.elte.softech.repository.EntryRepository;
import hu.elte.softech.repository.RankingRepository;
import hu.elte.softech.repository.TopicRepository;
import hu.elte.softech.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userR;

	@Autowired
	private RankingRepository rankingR;

	@Autowired
	private TopicRepository topicR;

	@Autowired
	private EntryRepository entryR;

	@Autowired
	private TopicService topicS;

	@Autowired
	private EntryService entryS;

	@Override
	public List<User> getAllUsers() {
		return userR.findAll();
	}

	@Override
	public List<UserTopicsAndEntrysAndFavs> getAllUsersWD() {
		List<UserTopicsAndEntrysAndFavs> lstUTEF = new ArrayList<UserTopicsAndEntrysAndFavs>();
		List<User> listU = getAllUsers();
		for(User u : listU) {
			UserTopicsAndEntrysAndFavs utef = new UserTopicsAndEntrysAndFavs();
			lstUTEF.add(findOneUserByUserIdWD(u.getId()));
		}
		return lstUTEF;
	}

	@Override
	public UserTopicsAndEntrysAndFavs findOneUserByUsernameWD(String username) {
		User user = userR.findUserByUsername(username);
		return null;
	}

	@Override
	public UserTopicsAndEntrysAndFavs findOneUserByUserIdWD(Long userId) {
		User user = findOneUserByUserId(userId);
		UserTopicsAndEntrysAndFavs utef = new UserTopicsAndEntrysAndFavs();
		utef.setUser(user);
		utef.setEntrys(getEntryListforUserWD(userId));
		utef.setTopics(getTopicListforUserWD(userId));
		utef.setFollowedTopics(topicS.getFollowTopicsByUser(userId));
		utef.setFavoriteEntrys(entryS.getFavEntrysByUser(userId));
		return utef;
	}

	@Override
	public User findOneUserByUsername(String username) {
		User user = userR.findUserByUsername(username);
		if(user == null) {
			//throw new UserNotFoundException("id-" + id);
		}
		return user;
	}

	@Override
	public User findOneUserByUserId(Long userId) {
		User user = userR.findById(userId).get();
		return user;
	}

	@Override
	public ResponseEntity<Object> createUser(User nU) {
		User savedUser = userR.save(nU);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public User createUserr(User newUser) {
		if(userR.findUserByUsername(newUser.getUsername()) == null && userR.findUserByEmail(newUser.getEmail()) == null)
		{
			return userR.save(newUser);
		}
		else {
			return null;
		}
	}

	@Override
	public User editUser(User eU, Long id) {
		return userR.findById(id).map(user -> {
	    	  user.setUsername(eU.getUsername());
	    	  user.setEmail(eU.getEmail());
	    	  user.setPassword(eU.getPassword());
	    	  user.setRole(eU.getRole());
	    	  return userR.save(user);
	      })
	      .orElseGet(() -> {
	    	  eU.setId(id);
	    	  return userR.save(eU);
	      });
	}

	@Override
	public ResponseEntity<Void> deleteUser(Long userId) {
		User user = userR.findById(userId).get();
		List<Entry> listEntry = entryR.findAllForUser(user);
		for(Entry e: listEntry) {
			entryS.deleteEntry(e.getId());
		}
		rankingR.delFromRankingByUser(userId);
		topicR.delFromFollowTopicByUser(userId);
		entryR.delFromFavoriteByUser(userId);
		entryR.delFromEntryByUser(userId);
		List<Topic> listTopic = topicR.findTopicListByUser(user);
		for(Topic t: listTopic) {
			topicS.deleteTopic(t.getId());
		}
		userR.deleteById(userId);
		return ResponseEntity.noContent().build();
	}


	//region utils
	private List<Entry> getEntryListforUserWD (Long userId){
		List<Entry> ans = new ArrayList<Entry>();
		for(Entry e: entryS.getEntrysByUser(userId)) {
			ans.add(e);
		}
		return ans;
	}

	private List<Topic> getTopicListforUserWD(Long userId) {
		List<Topic> ans = new ArrayList<Topic>();
		for(Topic t: topicS.getTopicsByUser(userId)) {
			ans.add(t);
		}
		return ans;
	}


	//end

}
