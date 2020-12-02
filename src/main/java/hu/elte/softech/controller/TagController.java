package hu.elte.softech.controller;

import java.util.List;

import hu.elte.softech.entity.*;
import hu.elte.softech.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

	@Autowired
	private TagService tagS;

	//GET Tags
	@RequestMapping(method=RequestMethod.GET, path="/tags")
	public List<Tag> retrieveAllTags() {
		return tagS.findAllTags();
	}

	//POST Tag
	@RequestMapping(method=RequestMethod.POST, path="/tag")
	public Tag postTag(@RequestBody Tag tagRequest) {
		return tagS.createTag(tagRequest);
	}

    //EDIT Tag
    @RequestMapping(method=RequestMethod.PUT,path="/tag/edit/{tagId}")
    public Tag updateTag(@PathVariable (value = "tagId") Long tagId,
                                 @RequestBody Tag tagRequest) {
        return tagS.editTag(tagId, tagRequest);
    }

    //GET Tag
	@RequestMapping(method=RequestMethod.GET, path="/tag/{tagId}")
	public Tag findOneTag(@PathVariable Long tagId){
		return tagS.findOneTag(tagId);
	}

	//DELETE
	@RequestMapping(method=RequestMethod.DELETE,path="/tag/delete/{tagId}")
	public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
	    return tagS.deleteTag(tagId);
	}

	//GET Topics By TagId
	@RequestMapping(method=RequestMethod.GET, path="/tag/{tagId}/topics")
	public List<TopicUserTagsEntrys> findOneTagTopics(@PathVariable Long tagId) {
		return tagS.findOneTagTopics(tagId);
	}






//	@RequestMapping(method=RequestMethod.GET,path="/tag/{id}")
//	public Set<Topic> retrieveAllTopicsForTag(@PathVariable Long id){
//		Optional<Tag> tag = tr.findById(id);
//		return tr.findTopicsOfTag(tag.get());
//	}

}
