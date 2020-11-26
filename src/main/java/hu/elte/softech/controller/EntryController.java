package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;
import hu.elte.softech.service.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class EntryController {
	
	@Autowired
	private EntryService es;
	
//	@Autowired
//	private EntryRepository er;
//	
//	@Autowired
//	private UserRepository ur;
//	
//	@Autowired
//	private TopicRepository tr;
//	
//	@Autowired
//	private TagRepository tgr;
	
	@RequestMapping(method=RequestMethod.GET, path="/entrys")
	public List<Entry> retrieveAllEntrys() {
		return es.findAllEntrys();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/user/{userId}/entrys")
	public Page<Entry> getAllEntrysByUserId(@PathVariable (value = "userId") Long userId,
                                                Pageable pageable) {
        return es.entrysByUser(userId, pageable);
    }
	
	@RequestMapping(method=RequestMethod.GET, path="/topic/{topicId}/entrys")
	public Page<Entry> getAllEntrysByTopicId(@PathVariable (value = "topicId") Long topicId,
                                                Pageable pageable) {
        return es.entrysByTopic(topicId, pageable);
    }
	
    @RequestMapping(method=RequestMethod.POST, path="/{userId}/{topicId}/entry")
    public Entry createEntry(@PathVariable (value = "userId") Long userId,@PathVariable (value = "topicId") Long topicId,
                                 @RequestBody Entry entry) {
        return es.createEntry(userId, topicId, entry);
    }
	
	@RequestMapping(method=RequestMethod.PUT,path="/user/{userId}/entry/{entryId}")
    public Entry updateEntryOfUser(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "entryId") Long entryId,
                                 @RequestBody Entry entryRequest) {
        return es.updateEntryOfUser(userId, entryId, entryRequest);
    }
	
	@RequestMapping(method=RequestMethod.PUT,path="/topic/{topicId}/entry/{entryId}")
    public Entry updateEntryOfTopic(@PathVariable (value = "topicId") Long topicId,
                                 @PathVariable (value = "entryId") Long entryId,
                                 @RequestBody Entry entryRequest) {
        return es.updateEntryOfTopic(topicId, entryId, entryRequest);
    }

    @RequestMapping(method=RequestMethod.DELETE,path="/topic/{topicId}/entry/{entryId}")
    public ResponseEntity<?> deleteEntryOfTopic(@PathVariable (value = "topicId") Long topicId,
                              @PathVariable (value = "entryId") Long entryId) {
        return es.deleteEntryOfTopic(topicId, entryId);
    }

    @RequestMapping(method=RequestMethod.DELETE,path="/user/{userId}/entry/{entryId}")
    public ResponseEntity<?> deleteEntryOfUser(@PathVariable (value = "userId") Long userId,
                              @PathVariable (value = "entryId") Long entryId) {
        return es.deleteEntryOfUser(userId, entryId);
    }
	
	
	
	
//	@RequestMapping(method=RequestMethod.GET, path="/entry/{id}")
//	public Entry retrieveOneEntry(@PathVariable Long id) {
//		return es.retrieveOneEntry(id);
//	}
	
//	@RequestMapping(method=RequestMethod.POST, path="/entry/one")
//	public Entry createEntry(@RequestBody Entry nE) {
//		return es.createEntry(nE);
//	}
	
//	@RequestMapping(method=RequestMethod.GET, path="/entrys/user/{username}")
//	public List<Entry> findOneUserEntrys(@PathVariable String username) {
//		return es.findOneUserEntrys(username);		
//	}
//	
//	@RequestMapping(method=RequestMethod.POST, path="/entry/new")
//	public ResponseEntity<Object> newEntry(@RequestBody Entry nE) {
//		return es.newEntry(nE);
//	}
//	
//	@RequestMapping(method=RequestMethod.PUT,path="/entry/edit/{id}")
//	public Entry editEntry(@RequestBody Entry eE, @PathVariable Long id) {
//	    return es.editEntry(eE, id);
//	  }
//	
//	@RequestMapping(method=RequestMethod.DELETE,path="/entry/delete/{id}")
//	public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
//	    return es.deleteEntry(id);
//	}
	
//	@RequestMapping(method=RequestMethod.GET, path="/entry/{id}")
//	public ResponseEntity<Object> retrieveEntrywithtags(@PathVariable Long id) {
//		Optional<Tag> tag = tgr.findById(tagid);
//		if(tag == null) {
//			//throw new UserNotFoundException("id-" + id);
//		}
//
//		return er.findAllForTag(tag.get());
//		
//	}

	
//	@RequestMapping(method=RequestMethod.GET, path="/entrys/topic/{topicid}")
//	public List<Entry> findOneTopicEntrys(@PathVariable Long topicid) {
//		Optional<Topic> topic = tr.findById(topicid);
//		if(topic == null) {
//			//throw new UserNotFoundException("id-" + id);
//		}
//
//		return er.findAllForTopic(topic.get());
//		
//	}
	
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
