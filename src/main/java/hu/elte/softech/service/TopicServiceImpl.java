package hu.elte.softech.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.Topic;
import hu.elte.softech.repository.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicRepository tr;

	@Override
	public ResponseEntity<Object> newTopic(Topic nT) {
		Topic savedTopic = tr.save(nT);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedTopic.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
