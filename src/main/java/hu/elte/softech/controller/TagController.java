package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;
import hu.elte.softech.service.TagService;

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
	private TagService ts;
	
//	@Autowired
//	private TagRepository tr;
//	
//	@Autowired
//	private TopicRepository tor;
	
	@RequestMapping(method=RequestMethod.GET, path="/tags")
	public List<Tag> retrieveAllTags() {
		return ts.allTags();
		//return tr.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/tag/one")
	public Tag newTag(@RequestBody Tag nTg) {
		return ts.createTag(nTg);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/tag/one/{id}")
	public Tag findOneTag(@PathVariable Long id) {
		return ts.findOneTag(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/tag/{id}/topics")
	public Set<Topic> findOneTagTopics(@PathVariable Long id) {
		return ts.findOneTagTopics(id);
	}
	
	
	
	//DELETE
	@RequestMapping(method=RequestMethod.DELETE,path="/tag/delete/{tagId}")
	public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
	    return ts.deleteTag(tagId);
	}
	
//	@RequestMapping(method=RequestMethod.GET,path="/tag/{id}")
//	public Set<Topic> retrieveAllTopicsForTag(@PathVariable Long id){
//		Optional<Tag> tag = tr.findById(id);
//		return tr.findTopicsOfTag(tag.get());
//	}

}
