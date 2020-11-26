package hu.elte.softech.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.TopicDTO;
import hu.elte.softech.entity.TopicTags;

public interface TopicService {
	
	List<Topic> allTopics();
	
	Page<Topic> getTopicsByUser(Long userId, Pageable pgb);
	
	Topic updateTopicOfUser(Long userId, Long topicId, Topic topic);

	Topic createTopicOfUser(Long userId, Topic topic);
	
	
	
	
	
	
	
	ResponseEntity<Object> newTopic(Topic nT);
	
	ResponseEntity<Object> createTopic(TopicDTO tdto);
	
	
	
	List<TopicTags> allTopicTags();
	
	ResponseEntity<Void> deleteTopic(Long id);

	Topic createTopic(Topic nTp);
	
	
	
	
	

}
