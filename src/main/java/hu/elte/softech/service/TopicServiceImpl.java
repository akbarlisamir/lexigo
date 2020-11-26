package hu.elte.softech.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.*;
import hu.elte.softech.repository.TagRepository;
import hu.elte.softech.repository.TopicRepository;
import hu.elte.softech.repository.UserRepository;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired 
	private TagService tgs;
	
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

//	@Override
//	public ResponseEntity<Object> createTopic(TopicDTO tdto) {
////		Topic topic = new Topic();
////		topic.setUser(ur.findById(tdto.getUser_id()).get());
////		topic.setValue(tdto.getTopic_value());
//		
//		Long id = (long) tr.findAll().size() + 1;
//		tr.ins(id, tdto.getTopic_value(), tdto.getUser_id());
//		
//		for(String val: tdto.getTags()) {
//			if(tgr.findTagByValue(val) != null) {
//				tgr.ins(id, tgr.findTagByValue(val).getId());
//			}
//			else {
//				
//			}
//		}
//		
//		List<String> lt = tdto.getTags();
//		Set<Tag> tagset = new HashSet<Tag>(); 
//		for(String e : lt) {
//			Tag t = tgr.findTagByValue(e);
//			if(t != null) {
//				tagset.add(t);
//			}
//			else {
//				Tag tt = tgr.save(t);
//				tagset.add(tt);
//			}
//		}
//
//		topic.setTags(tagset);
//		
//		Topic nT = tr.save(topic);
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(nT.getId()).toUri();
//			return ResponseEntity.created(location).build();
//	}

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

	@Override
	public List<TopicTags> allTopicTags() {
		List<Topic> ltt = tr.findAll();
		List<Tag> ltg = tgr.findAll();
		List<TopicTags> ltts = new ArrayList<TopicTags>();
		for(Topic t: ltt) {
			TopicTags tts = new TopicTags();
			tts.setUser(t.getUser());
			tts.setTopic(t);
			tts.setTagValues(t.getTags());
			ltts.add(tts);
		}
		
		return ltts;
		
		
	}

	@Override
	public ResponseEntity<Object> createTopic(TopicDTO tdto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic createTopic(Topic nTp) {
		return tr.save(nTp);
	}
	
	
	
	

	@Override
	public Page<Topic> getTopicsByUser(Long userId, Pageable pgb) {
		return tr.findByUserId(userId, pgb);
	}

	@Override
	public Topic updateTopicOfUser(Long userId, Long topicId, Topic topic) {
		if(!ur.existsById(userId)) {
            
        }
		Topic ut = tr.findById(topicId).get();
		ut.setValue(topic.getValue());
		return tr.save(ut);
	}

	@Override
	public Topic createTopicOfUser(Long userId, Topic topic) {
		topic.setUser(ur.findById(userId).get());
		return tr.save(topic);
	}

}
