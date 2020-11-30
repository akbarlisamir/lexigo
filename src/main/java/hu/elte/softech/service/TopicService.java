package hu.elte.softech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.TopicUserTagsEntrys;
import hu.elte.softech.entity.TopicWD;

public interface TopicService {

	//GET Topic With Details
	TopicUserTagsEntrys getTopicWD(Long topicId);

	//GET Topic
	Topic getTopic(Long topicId);

	//GET Topics With Details
	List<TopicUserTagsEntrys> allTopicsWD();

	//GET Topics
	List<Topic> allTopics();

	//GET Topics Of User By UserId
	List<Topic> getTopicsByUser(Long userId);

	//GET Topics Of User By Username
	List<Topic> getOneUserTopicsByUsername(String username);

	//DELETE Topic
	ResponseEntity<Void> deleteTopic(Long id);

	//POST Topic
	TopicWD createTopicOfUser(Long userId, TopicWD topicRequest);

	//EDIT Topic
	TopicWD editTopicOfUser(Long topicId, TopicWD topicRequest);

	//POST FollowTopic Follow
	List<Topic> followTopic(Long userId, Long topicId);

	//DELETE FollowTopic UnFollow
	List<Topic> unfollowTopic(Long userId, Long topicId);

	//GET FollowTopic By User
	List<Topic> getFollowTopicsByUser(Long userId);

	//Util
	public List<Tag> getTagListforTopicWD (Long topicId);

}
