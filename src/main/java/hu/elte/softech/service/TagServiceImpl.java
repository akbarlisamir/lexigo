package hu.elte.softech.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.Topic;
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

	@Override
	public ResponseEntity<Void> deleteTag(Long id) {
		tgr.del(id);
		tgr.deleteById(id);
	    
	    return ResponseEntity.noContent().build();
	}

}
