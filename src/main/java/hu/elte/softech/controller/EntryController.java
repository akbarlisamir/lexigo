package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;
import hu.elte.softech.service.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private EntryRepository er;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private TopicRepository tr;
	
	@Autowired
	private TagRepository tgr;
	
	@RequestMapping(method=RequestMethod.GET, path="/entrys")
	public List<Entry> retrieveAllEntrys() {
		return er.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/entry/{id}")
	public Entry retrieveOneEntry(@PathVariable Long id) {
		return es.retrieveOneEntry(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/entrys/user/{username}")
	public List<Entry> findOneUserEntrys(@PathVariable String username) {
		return es.findOneUserEntrys(username);		
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/entry/new")
	public ResponseEntity<Object> newEntry(@RequestBody Entry nE) {
		return es.newEntry(nE);
	}
	
	@RequestMapping(method=RequestMethod.PUT,path="/entry/edit/{id}")
	public Entry editEntry(@RequestBody Entry eE, @PathVariable Long id) {
	    return es.editEntry(eE, id);
	  }
	
	@RequestMapping(method=RequestMethod.DELETE,path="/entry/delete/{id}")
	public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
	    return es.deleteEntry(id);
	}
	
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

	
	@RequestMapping(method=RequestMethod.GET, path="/entrys/topic/{topicid}")
	public List<Entry> findOneTopicEntrys(@PathVariable Long topicid) {
		Optional<Topic> topic = tr.findById(topicid);
		if(topic == null) {
			//throw new UserNotFoundException("id-" + id);
		}

		return er.findAllForTopic(topic.get());
		
	}
	
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
	
	@RequestMapping(method=RequestMethod.GET, path="/entry/{username}/{topicid}")
	public List<Entry> findAllTopicUserEntry(@PathVariable String username, @PathVariable Long topicid) {
		Optional<Topic> topic = tr.findById(topicid);
		User user = ur.findUserByUsername(username);
		if(topic == null) {
			//throw new UserNotFoundException("id-" + id);
		}

		return er.findEntryForTopicUser(topic.get(), user);
		
	}

}
