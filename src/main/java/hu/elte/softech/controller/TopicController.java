package hu.elte.softech.controller;

import java.util.List;
import hu.elte.softech.entity.*;
import hu.elte.softech.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TopicController {
	
	@Autowired
	private TopicService ts;
	
	@RequestMapping(method=RequestMethod.GET, path="/topics")
	public List<Topic> retrieveAllTopics() {
		return ts.allTopics();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/user/{userId}/topics")
	public Page<Topic> getAllTopicsByUserId(@PathVariable (value = "userId") Long userId,
                                                Pageable pageable) {
        return ts.getTopicsByUser(userId, pageable);
    }
	
	@RequestMapping(method=RequestMethod.POST, path="/user/{userId}/topic")
    public Topic createTopic(@PathVariable (value = "userId") Long userId,
    		@RequestBody Topic topic) {
        return ts.createTopicOfUser(userId, topic);
    }
	
	@RequestMapping(method=RequestMethod.PUT,path="/user/{userId}/topic/{topicId}")
    public Topic updateTopicOfUser(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "topicId") Long topicId,
                                 @RequestBody Topic topicRequest) {
        return ts.updateTopicOfUser(userId, topicId, topicRequest);
    }
	
	//DELETE
	@RequestMapping(method=RequestMethod.DELETE,path="/topic/delete/{topicId}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long topicId) {
	    return ts.deleteTopic(topicId);
	}
	
	
	
	
	
	
//	@RequestMapping(method=RequestMethod.POST, path="/topic/one")
//	public Topic newTopic(@RequestBody Topic nTp) {
//		return ts.createTopic(nTp);
//	}
//	
//	@RequestMapping(method=RequestMethod.POST, path="/topic")
//	public ResponseEntity<Object> newTopic(@RequestBody TopicDTO nTDTO) {
//		return ts.createTopic(nTDTO);
//	}
//	
//	@RequestMapping(method=RequestMethod.DELETE,path="/topic/delete/{id}")
//	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
//	    return ts.deleteTopic(id);
//	}

}
