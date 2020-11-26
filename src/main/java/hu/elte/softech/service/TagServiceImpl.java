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
import hu.elte.softech.entity.User;
import hu.elte.softech.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagRepository tgr;
	
	@Override
	public List<Tag> allTags() {
		return tgr.findAll();
	}

	@Override
	public Tag findOneTag(Long id) {
		Optional<Tag> tag = tgr.findById(id);
		if(!tag.isPresent()) {
			//throw new UserNotFoundException("id-" + id);
		}
		return tag.get();
	}

	@Override
	public Set<Topic> findOneTagTopics(Long id) {
		Set<Topic> ts = new HashSet<Topic>();
		Optional<Tag> tag = tgr.findById(id);
		if(tag.isPresent()) {
			return tag.get().getTopics();
		}
		return null;
	}

	//DELETE
	@Override
	public ResponseEntity<Void> deleteTag(Long tagId) {
		tgr.delFromTopicTagByTag(tagId);
		tgr.deleteById(tagId);	    
	    return ResponseEntity.noContent().build();
	}

	@Override
	public Tag createTag(Tag tag) {
		return tgr.save(tag);
	}

}
