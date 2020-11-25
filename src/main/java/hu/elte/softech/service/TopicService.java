package hu.elte.softech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.TopicDTO;

public interface TopicService {
	
	ResponseEntity<Object> newTopic(Topic nT);
	
	ResponseEntity<Object> createTopic(TopicDTO tdto);
	
	List<Topic> allTopics();
	
	ResponseEntity<Void> deleteTopic(Long id);

}
