package hu.elte.softech.service;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.TopicDTO;
import hu.elte.softech.entity.User;
import hu.elte.softech.repository.TagRepository;
import hu.elte.softech.repository.TopicRepository;
import hu.elte.softech.repository.UserRepository;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicRepository tr;
	
	@Autowired
	private TagRepository tgr;
	
	@Autowired
	private UserRepository ur;

	@Override
	public ResponseEntity<Object> newTopic(Topic nT) {
		Topic savedTopic = tr.save(nT);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedTopic.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public ResponseEntity<Object> createTopic(TopicDTO tdto) {
		Topic topic = new Topic();
		topic.setUser(ur.findById(tdto.getUser_id()).get());
		topic.setValue(tdto.getTopic_value());
		List<String> lt = tdto.getTags();
		Set<Tag> tagset = new HashSet<Tag>(); 
		for(String e : lt) {
			Tag t = tgr.findTagByValue(e);
			if(t != null) {
				tagset.add(t);
			}
			else {
				Tag tt = tgr.save(t);
				tagset.add(tt);
			}
		}

		topic.setTags(tagset);
		
		Topic nT = tr.save(topic);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(nT.getId()).toUri();
			return ResponseEntity.created(location).build();
	}

	@Override
	public List<Topic> allTopics() {
		return tr.findAll();
	}
	
	@Override
	public ResponseEntity<Void> deleteTopic(Long id) {
		tr.del(id);
		tr.deleteById(id);
	    
	    return ResponseEntity.noContent().build();
	}

}
