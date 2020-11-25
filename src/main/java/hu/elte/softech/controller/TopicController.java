package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;
import hu.elte.softech.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class TopicController {
	
	@Autowired
	private TopicService ts;
	
	@Autowired
	private TopicRepository tr;
	
	@RequestMapping(method=RequestMethod.GET, path="/topics")
	public List<Topic> retrieveAllTopics() {
		//return ts.allTopics();
		return tr.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/topic")
	public ResponseEntity<Object> newTopic(@RequestBody TopicDTO nTDTO) {
		return ts.createTopic(nTDTO);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/topic/delete/{id}")
	public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
	    return ts.deleteTopic(id);
	}

}
