package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class TagController {
	
	@Autowired
	private TagRepository tr;
	
	@Autowired
	private TopicRepository tor;
	
	@RequestMapping(method=RequestMethod.GET, path="/tags")
	public List<Tag> retrieveAllTags() {
		return tr.findAll();
	}
	
//	@RequestMapping(method=RequestMethod.GET, path="/tag/{id}")
//	public Tag findOneTag(@PathVariable Long id) {
//		Optional<Tag> tag = tr.findById(id);
//		if(!tag.isPresent()) {
//			//throw new UserNotFoundException("id-" + id);
//		}
//		return tag.get();
//	}
	
//	@RequestMapping(method=RequestMethod.GET,path="/tag/{id}")
//	public Set<Topic> retrieveAllTopicsForTag(@PathVariable Long id){
//		Optional<Tag> tag = tr.findById(id);
//		return tr.findTopicsOfTag(tag.get());
//	}

}
