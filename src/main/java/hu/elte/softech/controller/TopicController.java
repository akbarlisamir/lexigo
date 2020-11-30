package hu.elte.softech.controller;

import java.util.List;
import hu.elte.softech.entity.*;
import hu.elte.softech.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TopicController {

	@Autowired
	private TopicService topicS;

	//GET Topics
	@RequestMapping(method=RequestMethod.GET, path="/topics")
	public List<Topic> retrieveAllTopics() {
		return topicS.allTopics();
	}

	//GET Topics With Details
	@RequestMapping(method=RequestMethod.GET, path="/topics/get")
	public List<TopicUserTagsEntrys> retrieveAllTopicsWD() {
		return topicS.allTopicsWD();
	}

	//GET Topic
	@RequestMapping(method=RequestMethod.GET, path="/topic/{topicId}")
	public Topic retrieveOneTopic(@PathVariable (value = "topicId") Long topicId) {
		return topicS.getTopic(topicId);
	}

	//GET Topic With Details
	@RequestMapping(method=RequestMethod.GET, path="/topic/{topicId}/get")
	public TopicUserTagsEntrys retrieveOneTopicsWD(@PathVariable (value = "topicId") Long topicId) {
		return topicS.getTopicWD(topicId);
	}

	//GET Topics Of User By Username
//	@RequestMapping(method=RequestMethod.GET, path="/user/{username}/topics")
//	public List<Topic> getOneUserTopicsByUserId(@PathVariable String username) {
//		return topicS.getOneUserTopicsByUsername(username);
//	}

	//GET Topics Of User By UserId
	@RequestMapping(method=RequestMethod.GET, path="/user/{userId}/topics")
	public List<Topic> getAllTopicsByUserId(@PathVariable (value = "userId") Long userId) {
        return topicS.getTopicsByUser(userId);
    }

	//POST Topic
	@RequestMapping(method=RequestMethod.POST, path="/{userId}/topic")
	public TopicWD createTopic(@PathVariable (value = "userId") Long userId,
			@RequestBody TopicWD topicRequest) {
		System.out.println(topicRequest.getTopic().getValue());
	    return topicS.createTopicOfUser(userId, topicRequest);
	}

	//EDIT Topic
	@RequestMapping(method=RequestMethod.PUT,path="/topic/edit/{topicId}")
    public TopicWD editTopicOfUser(@PathVariable (value = "topicId") Long topicId,
                                 @RequestBody TopicWD topicRequest) {
        return topicS.editTopicOfUser(topicId, topicRequest);
    }

	//DELETE Topic
	@RequestMapping(method=RequestMethod.DELETE,path="/topic/delete/{topicId}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long topicId) {
	    return topicS.deleteTopic(topicId);
	}

	//POST FollowTopic
	@RequestMapping(method=RequestMethod.POST,path="/{userId}/{topicId}/follow")
	public List<Topic> followTopic(@PathVariable (value = "userId") Long userId,
			@PathVariable (value = "topicId") Long topicId) {
		return topicS.followTopic(userId, topicId);
	}

	//DELETE FollowTopic
	@RequestMapping(method=RequestMethod.DELETE,path="/{userId}/{topicId}/follow")
	public List<Topic> unfollowTopic(@PathVariable (value = "userId") Long userId,
			@PathVariable (value = "topicId") Long topicId) {
		return topicS.unfollowTopic(userId, topicId);
	}

	//GET Topics Of User By UserId
	@RequestMapping(method=RequestMethod.GET, path="/user/{userId}/topics/follow")
	public List<Topic> getAllFollowTopicsByUser(@PathVariable (value = "userId") Long userId) {
        return topicS.getFollowTopicsByUser(userId);
    }










//	@RequestMapping(method=RequestMethod.POST, path="/topic/one")
//	public Topic newTopic(@RequestBody Topic nTp) {
//		return tagS.createTopic(nTp);
//	}
//
//	@RequestMapping(method=RequestMethod.POST, path="/topic")
//	public ResponseEntity<Object> newTopic(@RequestBody TopicDTO nTDTO) {
//		return tagS.createTopic(nTDTO);
//	}
//
//	@RequestMapping(method=RequestMethod.DELETE,path="/topic/delete/{id}")
//	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
//	    return tagS.deleteTopic(id);
//	}

}
