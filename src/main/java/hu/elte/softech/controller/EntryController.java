package hu.elte.softech.controller;

import java.util.List;
import java.util.Map;

import hu.elte.softech.entity.*;
import hu.elte.softech.service.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {

	@Autowired
	private EntryService entryS;



	// GET Entrys With Details  ----->   JSON MAP Stuff
	@RequestMapping(method = RequestMethod.GET, path = "/entrys/get/map")
	public Map<String, Object> mapEntys() {
		return entryS.mapEntry();
	}




	// GET Entrys
	@RequestMapping(method = RequestMethod.GET, path = "/entrys")
	public List<Entry> retrieveAllEntrys() {
		return entryS.findAllEntrys();
	}

	// GET Entrys With Details
	@RequestMapping(method = RequestMethod.GET, path = "/entrys/get")
	public List<EntryWD> retrieveAllEntrysWD() {
		return entryS.findAllEntrysWD();
	}

	// GET Entry
	@RequestMapping(method = RequestMethod.GET, path = "/entry/{entryId}")
	public Entry retrieveOneEntry(@PathVariable(value = "entryId") Long entryId) {
		return entryS.getEntry(entryId);
	}

	// GET Entry With Details
	@RequestMapping(method = RequestMethod.GET, path = "/entry/{entryId}/get")
	public EntryWD retrieveOneEntryWD(@PathVariable(value = "entryId") Long entryId) {
		return entryS.getEntryWD(entryId);
	}

	// GET Entrys Of User By UserId
	@RequestMapping(method = RequestMethod.GET, path = "/user/{userId}/entrys")
	public List<Entry> getAllEntrysByUserId(@PathVariable(value = "userId") Long userId) {
		return entryS.getEntrysByUser(userId);
	}

	// GET Entrys Of Topic By TopicId
	@RequestMapping(method = RequestMethod.GET, path = "/topic/{topicId}/entrys")
	public List<Entry> getAllEntrysByTopicId(@PathVariable(value = "topicId") Long topicId) {
		return entryS.getEntrysByTopic(topicId);
	}

	// POST Entry
	@RequestMapping(method = RequestMethod.POST, path = "/{userId}/{topicId}/entry")
	public Entry createEntry(@PathVariable(value = "userId") Long userId, @PathVariable(value = "topicId") Long topicId,
	        @RequestBody Entry entry) {
		return entryS.createEntry(userId, topicId, entry);
	}

	// EDIT Entry
	@RequestMapping(method = RequestMethod.PUT, path = "/entry/edit/{entryId}")
	public Entry updateEntryOfUser(@PathVariable(value = "entryId") Long entryId,
	        @RequestBody Entry entryRequest) {
		return entryS.editEntry(entryId, entryRequest);
	}

	// DELETE Entry
	@RequestMapping(method = RequestMethod.DELETE, path = "/entry/delete/{entryId}")
	public ResponseEntity<Void> deleteEntry(@PathVariable Long entryId) {
		return entryS.deleteEntry(entryId);
	}

	// POST Favorite
	@RequestMapping(method = RequestMethod.POST, path = "/{userId}/{entryId}/fav")
	public List<Entry> favEntry(@PathVariable(value = "userId") Long userId,
	        @PathVariable(value = "entryId") Long entryId) {
		return entryS.favEntry(userId, entryId);
	}

	// DELETE Favorite
	@RequestMapping(method = RequestMethod.DELETE, path = "/{userId}/{entryId}/fav")
	public List<Entry> unfollowEntry(@PathVariable(value = "userId") Long userId,
	        @PathVariable(value = "entryId") Long entryId) {
		return entryS.unfavEntry(userId, entryId);
	}

	// GET Favorites Of User By UserId
	@RequestMapping(method = RequestMethod.GET, path = "/user/{userId}/entrys/fav")
	public List<Entry> getAllFavEntrysByUser(@PathVariable(value = "userId") Long userId) {
		return entryS.getFavEntrysByUser(userId);
	}

	//POST Rank Up
	@RequestMapping(method = RequestMethod.POST, path = "/{userId}/{entryId}/rank/up")
	public List<Entry> rankEntryUp(@PathVariable(value = "userId") Long userId,
	        @PathVariable(value = "entryId") Long entryId) {
		return entryS.rankEntry(userId, entryId, true);
	}

	//POST Rank Down
	@RequestMapping(method = RequestMethod.POST, path = "/{userId}/{entryId}/rank/down")
	public List<Entry> rankEntryDown(@PathVariable(value = "userId") Long userId,
	        @PathVariable(value = "entryId") Long entryId) {
		return entryS.rankEntry(userId, entryId, false);
	}

	// GET Ranks Of User By UserId
	@RequestMapping(method = RequestMethod.GET, path = "/user/{userId}/entrys/rank")
	public List<Entry> getAllRankEntrysByUser(@PathVariable(value = "userId") Long userId) {
		return entryS.getRankEntrysByUser(userId);
	}

//	// DELETE Favorite
//	@RequestMapping(method = RequestMethod.DELETE, path = "/{userId}/{entryId}/fav")
//	public List<Entry> unfollowEntry(@PathVariable(value = "userId") Long userId,
//	        @PathVariable(value = "entryId") Long entryId) {
//		return entryS.unfavEntry(userId, entryId);
//	}
//
//	// GET Favorites Of User By UserId
//	@RequestMapping(method = RequestMethod.GET, path = "/user/{userId}/entrys/fav")
//	public List<Entry> getAllFollowTopicsByUser(@PathVariable(value = "userId") Long userId) {
//		return entryS.getFavEntrysByUser(userId);
//	}

//	@RequestMapping(method=RequestMethod.PUT,path="/user/{userId}/entry/{entryId}")
//    public Entry updateEntryOfUser(@PathVariable (value = "userId") Long userId,
//                                 @PathVariable (value = "entryId") Long entryId,
//                                 @RequestBody Entry entryRequest) {
//        return entryS.updateEntryOfUser(userId, entryId, entryRequest);
//    }

	@RequestMapping(method = RequestMethod.PUT, path = "/topic/{topicId}/entry/{entryId}")
	public Entry updateEntryOfTopic(@PathVariable(value = "topicId") Long topicId,
	        @PathVariable(value = "entryId") Long entryId,
	        @RequestBody Entry entryRequest) {
		return entryS.updateEntryOfTopic(topicId, entryId, entryRequest);
	}

//    @RequestMapping(method=RequestMethod.DELETE,path="/topic/{topicId}/entry/{entryId}")
//    public ResponseEntity<?> deleteEntryOfTopic(@PathVariable (value = "topicId") Long topicId,
//                              @PathVariable (value = "entryId") Long entryId) {
//        return entryS.deleteEntryOfTopic(topicId, entryId);
//    }
//
//    @RequestMapping(method=RequestMethod.DELETE,path="/user/{userId}/entry/{entryId}")
//    public ResponseEntity<?> deleteEntryOfUser(@PathVariable (value = "userId") Long userId,
//                              @PathVariable (value = "entryId") Long entryId) {
//        return entryS.deleteEntryOfUser(userId, entryId);
//    }

//	@RequestMapping(method=RequestMethod.GET, path="/entrys/tag/{tagid}")
//	public List<Entry> findOneTagEntrys(@PathVariable Long tagid) {
//		Optional<Tag> tag = tgr.findById(tagid);
//		if(tag == null) {
//			//throw new UserNotFoundException("id-" + id);
//		}
//
//		return er.findAllForTag(tag.get());
//
//	}

//	@RequestMapping(method=RequestMethod.GET, path="/entry/{username}/{topicid}")
//	public List<Entry> findAllTopicUserEntry(@PathVariable String username, @PathVariable Long topicid) {
//		Optional<Topic> topic = tr.findById(topicid);
//		User user = ur.findUserByUsername(username);
//		if(topic == null) {
//			//throw new UserNotFoundException("id-" + id);
//		}
//
//		return er.findEntryForTopicUser(topic.get(), user);
//
//	}

}
