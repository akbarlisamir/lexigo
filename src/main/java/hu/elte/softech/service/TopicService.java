package hu.elte.softech.service;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Topic;

public interface TopicService {
	
	ResponseEntity<Object> newTopic(Topic nT);

}
